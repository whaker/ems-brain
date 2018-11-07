
package com.identity.ems.processor.deeplearning;

import com.identity.ems.domain.BemsEnergyAnalysisData;
import com.identity.ems.domain.Building;
import com.identity.ems.mapper.BuildingMapper;
import com.identity.ems.service.deeplearning.EnergyFeedForwardService;
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

	@Autowired
    EnergyFeedForwardService energyFeedForwardService;

	public void runByBuilding(Building building) throws IOException {
	    // 전기

        energyFeedForwardService.run(
                realEnergyStatisticsWithPointService.getAll(
                        building.getBuildingIdx(),
                        BigInteger.valueOf(101001001),
                        "PT_OA_ALL"),
                "PowerMultiNetworkFile.zip"
        );

        // 가스
        energyFeedForwardService.run(
                realEnergyStatisticsWithPointService.getAll(
                        building.getBuildingIdx(),
                        BigInteger.valueOf(102001001),
                        "PT_OA_ALL"),
                "GasMultiNetworkFile.zip"
        );

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
