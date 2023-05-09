package ma.enset.ga.stringVersion;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Random;

public class tests extends Application {
    private static final int MAX_IT = 1000;
    private static final int GOAL_FITNESS = 0;
    private static final int UPDATE_INTERVAL_MS = 10;

    private Population population;
    private int currentIteration;
    private Label solutionLabel;
    private Label AnexLabel ;
    private Individual bestSolution;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        HBox topBox = new HBox();
        topBox.setAlignment(Pos.CENTER);
        topBox.setPadding(new Insets(10));
        topBox.setSpacing(10);
        Button startButton = new Button("Start");
        startButton.setFont(Font.font(20));
        startButton.setOnAction(event -> startGeneticAlgorithm());
        topBox.getChildren().addAll(startButton);
        root.setTop(topBox);

        VBox centerPane = new VBox();
        centerPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        solutionLabel = new Label();
        solutionLabel.setFont(Font.font(30));
        solutionLabel.setTextFill(Color.RED);

        AnexLabel = new Label();
        AnexLabel.setFont(Font.font(30));
        AnexLabel.setTextFill(Color.GREEN);

        centerPane.getChildren().addAll(solutionLabel, AnexLabel);
        centerPane.setAlignment(Pos.CENTER);
        root.setCenter(centerPane);

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private void startGeneticAlgorithm() {
        population = new Population();
        population.initialaizePopulation();
        population.calculateIndFintess();
        population.sortPopulation();
        currentIteration = 0;

        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= UPDATE_INTERVAL_MS) {
                    lastUpdate = now;
                    updateSolutionLabel();
                }
            }
        };

        timer.start();
    }

    private void updateSolutionLabel() {
        if (currentIteration < MAX_IT && population.getFitnessIndivd().getFitness() != GOAL_FITNESS) {
            population.selection();
            population.crossover();
            Random random = new Random();
            if (random.nextInt(101) < 50)
                population.mutation();
            population.calculateIndFintess();
            population.sortPopulation();
            bestSolution = population.getFitnessIndivd();
        }

        drawBestSolution();

        if (bestSolution.getFitness() == GOAL_FITNESS) {
            solutionLabel.setTextFill(Color.GREEN);
            solutionLabel.setText("Found solution in generation " + currentIteration);
            AnexLabel.setText(Arrays.toString(bestSolution.getGenes()));
            return;
        }

        currentIteration++;
        if (currentIteration >= MAX_IT) {
            solutionLabel.setTextFill(Color.RED);
            solutionLabel.setText("Max iterations reached without finding a solution");
        }
    }

    private void drawBestSolution() {
        if (bestSolution != null) {
            solutionLabel.setText(Arrays.toString(bestSolution.getGenes()));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

