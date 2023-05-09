package ma.enset.ga.stringVersion;

import java.util.*;

public class Population {

    List<Individual> individuals=new ArrayList<>();
    Individual firstFitness;
    Individual secondFitness;
    Random rnd=new Random();
    public void initialaizePopulation(){
        for (int i=0;i<30;i++){
           individuals.add(new Individual());
        }
    }
    public void calculateIndFintess(){
        for (int i=0;i<30;i++){
            individuals.get(i).calculateFitness();
        }

    }
    public void selection(){
        firstFitness=individuals.get(0);
        secondFitness=individuals.get(1);
        /*for (int i=0;i<30;i++){
            System.out.println(individuals.get(i).getFitness());
        }*/
    }
    //croisement
    public void crossover(){

        int pointCroisment=rnd.nextInt(4)+1;
        Individual individual1=new Individual();
        Individual individual2=new Individual();
        for (int i=0;i<individual1.getGenes().length;i++) {
            individual1.getGenes()[i]=firstFitness.getGenes()[i];
            individual2.getGenes()[i]=secondFitness.getGenes()[i];
        }
        for (int i=0;i<pointCroisment;i++) {
            individual1.getGenes()[i]=secondFitness.getGenes()[i];
            individual2.getGenes()[i]=firstFitness.getGenes()[i];
        }
        //System.out.println(Arrays.toString(individual1.getGenes()));
        //System.out.println(Arrays.toString(individual2.getGenes()));

        individuals.set(individuals.size()-2,individual1);
        individuals.set(individuals.size()-1,individual2);
    }
    public void mutation(){
       int mutationIndex=rnd.nextInt(5);
       int charIndex=rnd.nextInt(26);
        individuals.get(individuals.size()-2).getGenes()[mutationIndex]=Individual.chars.charAt(charIndex);
        individuals.get(individuals.size()-1).getGenes()[mutationIndex]=Individual.chars.charAt(charIndex);
    }

    public List<Individual> getIndividuals() {
        return individuals;
    }
    public void sortPopulation(){
        Collections.sort(individuals);
    }
    public Individual getFitnessIndivd(){
        return individuals.get(0);
    }
}
