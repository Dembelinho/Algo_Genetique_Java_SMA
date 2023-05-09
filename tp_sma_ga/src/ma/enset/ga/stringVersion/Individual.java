package ma.enset.ga.stringVersion;

import java.util.Random;

public class Individual implements Comparable{
    String goal="salam";
    static String chars="abcdefghijklmnopqrstuvwxyz";
    private char genes[]=new char[5];
    private int fitness;

    public Individual() {
        Random rnd=new Random();
        for (int i=0;i<genes.length;i++){
            genes[i]= chars.charAt(rnd.nextInt(5));
        }
    }
    public void calculateFitness(){
        fitness=0;
        for (int i=0;i< genes.length;i++) {
            fitness+=Math.abs((int)genes[i]-(int)goal.charAt(i));
        }
    }

    public int getFitness() {
        return fitness;
    }

    public char[] getGenes() {
        return genes;
    }

    @Override
    public int compareTo(Object o) {
        Individual individual=(Individual) o;
        if (this.fitness>individual.fitness)
            return 1;
        else if(this.fitness<individual.fitness){
            return -1;
        }else
            return 0;
    }
}
