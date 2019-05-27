package com.grab.helpers;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.stream.Collectors;

public class ListTable {
    private WebElement webElement;

    public ListTable(WebElement webElement) {
        this.webElement = webElement;
    }

    private List<String> getHeaders() {
        return webElement.findElements(By.xpath("//thead/tr/th")).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    private int rowsCount() {
        return webElement.findElements(By.xpath("//tbody/tr")).size();
    }

    private List<String> getRow(int rowNumber) {
        return webElement.findElements(By.xpath("//tbody/tr[" + (rowNumber + 1) +"]/td")).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public Multimap<String, String> getDataGroupedByColumn() {
        List<String> headers = getHeaders();
        Multimap<String, String> result = ArrayListMultimap.create();
        for (int i = 0; i < rowsCount(); i++) {
            List<String> row = getRow(i);
            for (int j = 0; j < row.size();j++) {
                result.put(headers.get(j), row.get(j));
            }
        }
        return result;
    }
}
