package com.brandon.apportionmentcalculator;

import android.widget.EditText;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;

public class HamiltonMethod {

    private static BigDecimal total_quota;
    private static int total_population;
    private static int amount_of_seats;
    private static int amount_of_states;
    private static BigDecimal finalDivisor;
    private static ArrayList<BigDecimal> state_populations = new ArrayList<>();
    private static ArrayList<BigDecimal> quotas = new ArrayList<>();
    private static ArrayList<Integer> initial_fair_shares = new ArrayList<>();
    private static ArrayList<Integer> final_fair_shares = new ArrayList<>();

    //output values
    private static ArrayList<EditText> output_quotas = new ArrayList<>();
    private static ArrayList<EditText> output_initial_fair_shares = new ArrayList<>();
    private static ArrayList<EditText> output_final_fair_shares = new ArrayList<>();

    //bigdecimal helper
    private static MathContext mc = new MathContext(7);

    public HamiltonMethod() {
        //default constructor
    }

    public HamiltonMethod(int amountOfStates, int amountOfSeats, ArrayList<EditText> populationCol, ArrayList<EditText> quotaCol, ArrayList<EditText> initialCol, ArrayList<EditText> finalCol) {

        state_populations.clear();
        quotas.clear();
        initial_fair_shares.clear();
        final_fair_shares.clear();
        output_quotas.clear();
        output_initial_fair_shares.clear();
        output_final_fair_shares.clear();


        //setup amount of states
        this.amount_of_states = amountOfStates;
        //setup amount of seats
        this.amount_of_seats = amountOfSeats;
        //setup state populations
        for (int i = 0; i < amountOfStates; i++) {
            BigDecimal bd = new BigDecimal(populationCol.get(i).getText().toString());
            state_populations.add(bd);
        }
        //setup output_quotas
        for (int i = 0; i < amountOfStates; i++) {
            output_quotas.add(quotaCol.get(i));
        }
        //setup output_initial_fair_shares
        for (int i = 0; i < amountOfStates; i++) {
            output_initial_fair_shares.add(initialCol.get(i));
        }
        //setup output_final_fair_shares
        for (int i = 0; i < amountOfStates; i++) {
            output_final_fair_shares.add(finalCol.get(i));
        }
    }

    public void hamilton() {

        //calculate quotas for each state
        BigDecimal totalPopulation = new BigDecimal(0); //first get total population of every state
        for (int i = 0; i < amount_of_states; i++) {
            BigDecimal currentState = state_populations.get(i);
            totalPopulation = totalPopulation.add(currentState);
        }

        total_population = totalPopulation.intValue();

        BigDecimal seatsBd = new BigDecimal(amount_of_seats);
        BigDecimal divisor = totalPopulation.divide(seatsBd, mc);
        BigDecimal totalQuota = new BigDecimal(0);
        finalDivisor = divisor;
        for (int i = 0; i < amount_of_states; i++) {
            BigDecimal stateQuota = state_populations.get(i).divide(divisor, mc);
            totalQuota = totalQuota.add(stateQuota, mc);
            quotas.add(stateQuota);
            output_quotas.get(i).setText("" + stateQuota);
        }

        total_quota = totalQuota;

        //calculate initial fair shares for each state (floor the quotas)
        for (int i = 0; i < amount_of_states; i++) {
            double temp = Math.floor(quotas.get(i).doubleValue()); //round down
            int stateInitialFairShare = (int) temp;
            initial_fair_shares.add(stateInitialFairShare);
            output_initial_fair_shares.get(i).setText("" + stateInitialFairShare);
        }

        //calculate final fair shares for each state (calculateFinalFs method)

        //first get decimal remainder of each quota and the sum of initial values
        ArrayList<Double> remainders = new ArrayList<>();
        int initialSum = 0;
        for (int i = 0; i < amount_of_states; i++) {
            initialSum += Math.floor(quotas.get(i).doubleValue());
            double temp = quotas.get(i).doubleValue() - Math.floor(quotas.get(i).doubleValue());
            remainders.add(temp);
        }

        for (int i = 0; i < amount_of_states; i++) {
            final_fair_shares.add((int)Math.floor(quotas.get(i).doubleValue()));
            initial_fair_shares.add((int)Math.floor(quotas.get(i).doubleValue()));
        }

        //this will change the final_fair_shares values
        calculateFinalFs(remainders, final_fair_shares, initialSum);

        //set output for output_initial_fair_shares and output_final_fair_shares
        for (int i = 0; i < amount_of_states; i++) {
            output_initial_fair_shares.get(i).setText("" + initial_fair_shares.get(i));
            output_final_fair_shares.get(i).setText("" + final_fair_shares.get(i));
        }


    }

    //params (decimal remainder of each quota, final_values, sum of initial fair shares
    public static void calculateFinalFs(ArrayList<Double> remainders, ArrayList<Integer> finalValues, int initialSum ) {
        if (initialSum != amount_of_seats) {
            //get the highest remainder
            double max = Collections.max(remainders);
            //locate the index of the highest decimal
            int index = 0;
            for (int i = 0; i < amount_of_states; i++) {
                if (max == remainders.get(i)) {
                    index = i;
                    break;
                }
            }
            //increase the amount of seats of the list at the index of the highest decimal
            finalValues.set(index, finalValues.get(index) + 1);
            //lower the index of the decimal location in the decimal array (so we can re-use this method)
            remainders.set(index, 0.0);
            //check for more inequalities
            initialSum += 1;
            calculateFinalFs(remainders, finalValues, initialSum);


        }
    }

    public ArrayList<EditText> getOutput_quotas() {
        return output_quotas;
    }

    public ArrayList<EditText> getOutput_initial_fair_shares() {
        return output_initial_fair_shares;
    }

    public BigDecimal getDivisor() {
        return finalDivisor;
    }

    public ArrayList<EditText> getOutput_final_fair_shares() {
        return output_final_fair_shares;
    }

    public int getTotal_population() {return total_population; }

    public BigDecimal getTotal_quota() {return total_quota; }

    public int getInitialFs() {
        int total = 0;
        for (int i = 0; i < output_initial_fair_shares.size(); i++) {
            String value = output_initial_fair_shares.get(i).getText().toString();
            total += Integer.parseInt(value);
        }
        return total;
    }

    public int getFinalFs() {
        int total = 0;
        for (int i = 0; i < output_final_fair_shares.size(); i++) {
            String value = output_final_fair_shares.get(i).getText().toString();
            total += Integer.parseInt(value);
        }
        return total;
    }


}
