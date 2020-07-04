package app;

import exception.ParserException;
import model.Currency;
import model.CurrencyRate;
import parser.Parser;
import parser.impl.JaxbParser;
import util.DownloaderUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.function.Consumer;

public class CurrencyConverter {
    private static final String URL = "http://www.cbr.ru/scripts/XML_daily.asp";
    private static final String UPLOAD_ERROR_MESSAGE = "Извините, возникла проблема с загрузкой данных...";
    private static final String CONTINUE_USED_APP = "д";

    public static void main(String[] args) {
        Consumer<String> printer = System.out::println;
        Parser parser = new JaxbParser();
        Object object = null;

        try {
            File file = DownloaderUtil.downloadFile(URL);
            object = parser.getObject(file, CurrencyRate.class);
        } catch (IOException | ParserException e) {
            printer.accept(UPLOAD_ERROR_MESSAGE);
            //вывод в log сообщения об ошибке
            return;
        }

        if (!(object instanceof CurrencyRate)) {
            printer.accept(UPLOAD_ERROR_MESSAGE);
            //вывод в log сообщения
            return;
        }
        CurrencyRate currencyRate = (CurrencyRate) object;

        String answerUser = CONTINUE_USED_APP;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (CONTINUE_USED_APP.equalsIgnoreCase(answerUser)) {
                printer.accept("Перечень кодов доступных валют для конвертации в рубли (Информация представлена в виде: Названия валюты - Код валюты):");

                currencyRate.getCurrencies().forEach(i -> printer.accept(String.format("%s - %s", i.getName(), i.getCharCode())));
                printer.accept("\nПожалуйста, введите код конвертируемой валюты в рубли.");

                String currencyNameFromConvert = reader.readLine();

                Optional<Currency> formCurrency = currencyRate.getCurrencyByCode(currencyNameFromConvert);

                if (formCurrency.isPresent()) {
                    Currency currency = formCurrency.get();
                    String ruble = currency.getNominal() == 1 ? "рублю" : "рублям";
                    printer.accept(String.format("%s %s к %s %s%n", currency.getValue(), currency.getName(), currency.getNominal(), ruble));
                } else {
                    printer.accept("***Вы ввели не верный код валюты***");
                }
                printer.accept("Если желаете продолжить введите - д. Для выхода нажмите Enter.");
                answerUser = reader.readLine();
            }
        } catch (IOException e) {
            printer.accept("Ошибка!");
            //вывод в log сообщения об ошибке
            return;
        }
    }
}