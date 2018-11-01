
package com.identity.ems.processor.deeplearning;

import com.identity.ems.domain.BemsEnergyAnalysisData;
import com.identity.ems.domain.Building;
import com.identity.ems.mapper.BuildingMapper;
import com.identity.ems.service.statistics.RealEnergyStatisticsWithPointService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

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

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Random;

@Service
public class EnergyFeedForwardProcessor {
	private static final Logger logger = LoggerFactory.getLogger(EnergyFeedForwardProcessor.class);

	@Autowired
	BuildingMapper buildingMapper;

	@Autowired
    RealEnergyStatisticsWithPointService realEnergyStatisticsWithPointService;

	public final int seed = 76655;

    //Number of epochs (full passes of the data)
    public final int nEpochs = 200;

    // each epoch has nSamples/batchSize parameter updates
    public final int batchSize = 100;

    public final int nSamples = 1000;

    //Network learning rate
    public final double learningRate = 0.01;
    public final String multiLayerNetworkFilepath = "../shared/EnergyMultiLayerNetwork.zip";

    // The range of the sample data, data in range (0-1 is sensitive for NN, you can try other ranges and see how it effects the results
    // also try changing the range along with changing the activation function
    public int MIN_RANGE = 0;
    public int MAX_RANGE = 3;


    public final int numInputs = 1;
    public final int numOutputs = 1;


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
            logger.info("Restored MultiLayerNetwork: " +  multiLayerNetworkFilepath);
            return ModelSerializer.restoreMultiLayerNetwork(multiLayerNetworkFile);
        }

        return new MultiLayerNetwork(getDeepDenseLayerNetworkConfiguration());
    }

    private void saveMultiLayerNetwork(MultiLayerNetwork net) throws IOException {
        File multiLayerNetworkFile = new File(multiLayerNetworkFilepath);
        boolean saveUpdater = true;
        ModelSerializer.writeModel(net, multiLayerNetworkFile, saveUpdater);
    }

    private MultiLayerNetwork restoreMutilLayerNetwork() throws IOException {
        File multiLayerNetworkFile = new File(multiLayerNetworkFilepath);
        return ModelSerializer.restoreMultiLayerNetwork(multiLayerNetworkFile);
    }

	private DataSetIterator getTestForTrainingMultiData(int batchSize){
        final Random rng = new Random(seed);

        double [] sum = new double[nSamples];
        double [] input1 = new double[nSamples];
        double [] input2 = new double[nSamples];
        for (int i= 0; i< nSamples; i++) {
            input1[i] = MIN_RANGE + (MAX_RANGE - MIN_RANGE) * rng.nextDouble();
            input2[i] =  MIN_RANGE + (MAX_RANGE - MIN_RANGE) * rng.nextDouble();
            sum[i] = input1[i] + input2[i];

            logger.info("{}, {}, {}, {}", input1[i], input2[i], sum[i], rng.nextDouble());
        }

        INDArray inputNDArray1 = Nd4j.create(input1, new int[]{nSamples,1});
        // INDArray inputNDArray2 = Nd4j.create(input2, new int[]{nSamples,1});
        // INDArray inputNDArray = Nd4j.hstack(inputNDArray1,inputNDArray2);
        INDArray outPut = Nd4j.create(sum, new int[]{nSamples, 1});
        DataSet dataSet = new DataSet(inputNDArray1, outPut);
        List<DataSet> listDs = dataSet.asList();

        Collections.shuffle(listDs, rng);
        return new ListDataSetIterator(listDs, batchSize);
    }

    private DataSetIterator getTrainingData(int batchSize, Building building) {
        List<BemsEnergyAnalysisData> analysisDataList = realEnergyStatisticsWithPointService.getAll(building.getBuildingIdx(), BigInteger.valueOf(101001001), "PT_OA_ALL");
        if (analysisDataList == null) {
            logger.info("Training data is null");
            return null;
        }

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
	
	public void runByBuilding(Building building) throws IOException {
         DataSetIterator iterator = getTrainingData(batchSize, building);
        if (iterator == null)
            return;

        final MultiLayerNetwork net = createMultiLayerNetwork();
        net.init();
        net.setListeners(new ScoreIterationListener(1));

        for (int i = 0; i < nEpochs; i++) {
            iterator.reset();
            net.fit(iterator);
        }

        saveMultiLayerNetwork(net);
        testMultiLayerNetwork();
    }

    private void testMultiLayerNetwork() {
        try {
            MultiLayerNetwork net = restoreMutilLayerNetwork();

            logger.info("MultiNetwork Output : " + net.output(Nd4j.create(
                    new double[]{0.3353434, 1.35343, 0.11423, 1.252325, 0.15235, 3.53434 }, new int[]{6, 1}), false)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(String date, String time) {
        logger.debug("Energy feed forward started. date : {}, time : {}, periodType : {}", date, time);
        try {
            List<Building> buildings = buildingMapper.findAll();
            buildings.forEach(building -> {
                try {
                    runByBuilding(building);
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error("Energy feed forward run failed!. errorMessage : " + e.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Energy feed forward run failed!. errorMessage : " + e.getMessage());
        }
        logger.debug("Energy feed forward have been completed. date : {}, time : {}, periodType : {}", date, time);
    }
}
