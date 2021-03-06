package com.identity.ems.service.deeplearning;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.identity.ems.domain.BemsEnergyAnalysisData;

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
import org.nd4j.linalg.cpu.nativecpu.NDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Nesterovs;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
	private final String multiLayerNetworkDir= "../shared/deep-learning/";

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

	private MultiLayerNetwork createMultiLayerNetwork(String filepath) throws IOException {
		File multiLayerNetworkFile = new File(filepath);
		if (multiLayerNetworkFile.isFile()) {
			logger.info("Restored MultiLayerNetwork: " + filepath);
			return ModelSerializer.restoreMultiLayerNetwork(multiLayerNetworkFile);
		}

		return new MultiLayerNetwork(getDeepDenseLayerNetworkConfiguration());
	}

	public void saveMultiLayerNetwork(MultiLayerNetwork net, String filePath) throws IOException {
		File multiLayerNetworkFile = new File(filePath);
		boolean saveUpdater = true;
		ModelSerializer.writeModel(net, multiLayerNetworkFile, saveUpdater);
	}

	public MultiLayerNetwork restoreMultiLayerNetwork(String filePath) throws IOException {
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
			MultiLayerNetwork net = restoreMultiLayerNetwork(filePath);
			INDArray array = net.output(Nd4j.create(
					new double[]{ 0.3353434 },
					new int[]{ 1, 1 }), false);
			logger.info("MultiNetwork Output[0]: " + array.getDouble(0));
			array = net.output(Nd4j.create(
					new double[]{ 1.3534334 },
					new int[]{ 1, 1 }), false);
			logger.info("MultiNetwork Output[0]: " + array.getDouble(0));
			array = net.output(Nd4j.create(
					new double[]{ 0.11423 },
					new int[]{ 1, 1 }), false);
			logger.info("MultiNetwork Output[0]: " + array.getDouble(0));
			array = net.output(Nd4j.create(
					new double[]{ 1.252325 },
					new int[]{ 1, 1 }), false);
			logger.info("MultiNetwork Output[0]: " + array.getDouble(0));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run(List<BemsEnergyAnalysisData> analysisDataList, String filename) throws IOException {
	    if (analysisDataList == null) {
			logger.info("Analysis data is null");
			return;
		}

		String filePath = multiLayerNetworkDir + filename;
		DataSetIterator dataIterator = getTrainingData(analysisDataList);

		final MultiLayerNetwork net = createMultiLayerNetwork(filePath);
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
