package com.identity.ems.service.deeplearning;

import com.identity.ems.domain.BemsEnergyAnalysisData;
import com.identity.ems.domain.Building;
import org.deeplearning4j.datasets.iterator.impl.ListDataSetIterator;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Nesterovs;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class EnergyFeedForwardService {
	private static final Logger logger = LoggerFactory.getLogger(EnergyFeedForwardService.class);

	private final int seed = 76655;

	//Number of epochs (full passes of the data)
	private final int nEpochs = 200;

	// each epoch has nSamples/batchSize parameter updates
	private int batchSize = 100;

	//Network learning rate
	private final double learningRate = 0.01;
	private final String multiLayerNetworkFilepath = "../shared/EnergyMultiLayerNetwork.zip";

	public final int numInputs = 1;
	public final int numOutputs = 1;

	public void setBatchSize(int size) {
		this.batchSize = size;
	}

	public int getBatchSize() {
		return this.batchSize;
	}

	private MultiLayerConfiguration getDeepDenseLayerNetworkConfiguration2() {
		final int numHiddenNodes = 10;
		return new NeuralNetConfiguration.Builder()
				.seed(seed)
				.weightInit(WeightInit.XAVIER)
				.optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
				.updater(new Nesterovs(learningRate, 0.9))
				.list()
				.layer(0, new DenseLayer.Builder().nIn(numInputs).nOut(numHiddenNodes)
						.activation(Activation.TANH)
						.build())
				.layer(1, new DenseLayer.Builder().nIn(numHiddenNodes).nOut(numHiddenNodes)
						.activation(Activation.TANH)
						.build())
				.layer(2, new OutputLayer.Builder(LossFunctions.LossFunction.MSE)
						.activation(Activation.IDENTITY)
						.nIn(numHiddenNodes).nOut(numOutputs)
						.build())
				.pretrain(false).backprop(true).build();
	}

	private MultiLayerConfiguration getDeepDenseLayerNetworkConfiguration() {
		final int numHiddenNodes = 10;
		return new NeuralNetConfiguration.Builder()
				.seed(seed)
				.weightInit(WeightInit.XAVIER)
				.optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
				.updater(new Nesterovs(learningRate, 0.9))
				.list()
				.layer(0, new DenseLayer.Builder().nIn(numInputs).nOut(numHiddenNodes)
						.activation(Activation.TANH)
						.build())
				.layer(1, new OutputLayer.Builder(LossFunctions.LossFunction.MSE)
						.activation(Activation.IDENTITY)
						.nIn(numHiddenNodes)
						.nOut(numOutputs).build())
				.pretrain(false).backprop(true).build();
	}

	private MultiLayerNetwork createMultiLayerNetwork() throws IOException {
		File multiLayerNetworkFile = new File(multiLayerNetworkFilepath);
		if (multiLayerNetworkFile.isFile()) {
			logger.info("Restored MultiLayerNetwork: " + multiLayerNetworkFilepath);
			return ModelSerializer.restoreMultiLayerNetwork(multiLayerNetworkFile);
		}

		return new MultiLayerNetwork(getDeepDenseLayerNetworkConfiguration());
	}

	public void saveMultiLayerNetwork(MultiLayerNetwork net, String filePath) throws IOException {
		File multiLayerNetworkFile = new File(filePath);
		boolean saveUpdater = true;
		ModelSerializer.writeModel(net, multiLayerNetworkFile, saveUpdater);
	}

	public MultiLayerNetwork restoreMutilLayerNetwork(String filePath) throws IOException {
		File multiLayerNetworkFile = new File(filePath);
		return ModelSerializer.restoreMultiLayerNetwork(multiLayerNetworkFile);
	}

	private DataSetIterator getTrainingData(List<BemsEnergyAnalysisData> analysisDataList) {
		double[] inputs = new double[analysisDataList.size()];
		double[] outputs = new double[analysisDataList.size()];

		for (int index = 0; index < analysisDataList.size(); index++) {
			inputs[index] = analysisDataList.get(index).getInputValue();
			outputs[index] = analysisDataList.get(index).getOutputValue();
		}

		INDArray inputNDArray = Nd4j.create(inputs, new int[]{inputs.length, 1});
		INDArray outputNDArray = Nd4j.create(outputs, new int[]{outputs.length, 1});
		DataSet dataSet = new DataSet(inputNDArray, outputNDArray);
		List<DataSet> listDs = dataSet.asList();

		final Random rng = new Random(seed);
		Collections.shuffle(listDs, rng);

		return new ListDataSetIterator(listDs, batchSize);
	}

	private void testMultiLayerNetwork(String filePath) {
		try {
			MultiLayerNetwork net = restoreMutilLayerNetwork(filePath);
			logger.info("MultiNetwork Output : " + net.output(Nd4j.create(
					new double[]{0.3353434, 1.35343, 0.11423, 1.252325, 0.15235, 3.53434 }, new int[]{6, 1}), false)
			);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run(List<BemsEnergyAnalysisData> analysisDataList, String filename) throws IOException {
		String filePath = "../shared/" + filename;

	    if (analysisDataList == null) {
			logger.info("Analysis data is null");
			return;
		}

		DataSetIterator dataIterator = getTrainingData(analysisDataList);
		if (dataIterator == null)
			return;

		final MultiLayerNetwork net = createMultiLayerNetwork();
		net.init();
		net.setListeners(new ScoreIterationListener(1));

		for (int i = 0; i < nEpochs; i++) {
			dataIterator.reset();
			net.fit(dataIterator);
		}

		saveMultiLayerNetwork(net, filePath);
		testMultiLayerNetwork(filePath);
	}
}
