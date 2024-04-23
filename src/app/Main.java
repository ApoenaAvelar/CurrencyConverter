package app;

import models.ClientConverter;
import models.CurrencyExchange;
import models.FileRecorder;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ClientConverter client = new ClientConverter();
        boolean continueApp = true;
        String baseCurrency = "";
        String targetCurrency = "";
        DecimalFormat format = new DecimalFormat("#.##");

        while (continueApp) {


            UiScreen.showScreen();
            Scanner scanner = new Scanner(System.in);


            System.out.println("Digite uma opção válida -> ");
            int option = scanner.nextInt();


            double value ;


            //create an enhanced switch case with nine option using the variable option
            switch (option) {
                case 1 -> {
                    baseCurrency = "BRL";
                    targetCurrency = "USD";
                }
                case 2 -> {
                    baseCurrency = "USD";
                    targetCurrency = "BRL";
                }
                case 3 -> {
                    baseCurrency = "BRL";
                    targetCurrency = "CLP";
                }
                case 4 -> {
                    baseCurrency = "CLP";
                    targetCurrency = "BRL";
                }
                case 5 -> {
                    baseCurrency = "COP";
                    targetCurrency = "BOB";
                }
                case 6 -> {
                    baseCurrency = "BOB";
                    targetCurrency = "COP";
                }
                case 7 -> {
                    baseCurrency = "BRL";
                    targetCurrency = "EUR";
                }
                case 8 -> {
                    baseCurrency = "EUR";
                    targetCurrency = "BRL";
                }
                case 0 -> continueApp = false;


                default -> throw new IllegalStateException("Valor inesperado: " + option + "Encerrando a aplicacao");
            }


            try {

                if (continueApp){
                    System.out.println("Digite o valor a ser convertido-> ");
                    value = scanner.nextDouble();
                } else {
                    System.out.println("Encerrando a aplicação.");
                    continue;
                }


                CurrencyExchange newExchange = client.searchExchange(baseCurrency, targetCurrency);
                System.out.println("Consultando...");
                String result = format.format(Double.parseDouble(newExchange.conversion_rate()) * value);
                System.out.println(" Valor convertido: " + targetCurrency + " " +
                        result);
                FileRecorder gerador = new FileRecorder();
                gerador.saveJson(newExchange);
            } catch (RuntimeException | IOException e) {
                System.out.println("Impossível realizar a operacao com os parâmetros escolhidos");
                System.out.println("Favor rever os dados");
            }


        }
    }
}
