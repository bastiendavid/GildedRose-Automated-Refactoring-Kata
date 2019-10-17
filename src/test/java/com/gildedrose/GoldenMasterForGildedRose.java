package com.gildedrose;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;

public class GoldenMasterForGildedRose {

    @Test
    void goldenMaster() {
        Item[] items = new Item[]{
                new Item("+5 Dexterity Vest", 10, 20), //
                new Item("Aged Brie", 2, 0), //
                new Item("Elixir of the Mongoose", 5, 7), //
                new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                // this conjured item does not work properly yet
                new Item("Conjured Mana Cake", 3, 6)};

        GildedRose app = new GildedRose(items);

        int days = 20;

        StringWriter goldenMaster = new StringWriter();
        for (int i = 0; i < days; i++) {
            appendLine(goldenMaster, "-------- day " + i + " --------");
            appendLine(goldenMaster, "name, sellIn, quality");
            for (Item item : items) {
                appendLine(goldenMaster, item.toString());
            }
            goldenMaster.append("");
            app.updateQuality();
        }

        Approvals.verify(goldenMaster);
    }

    private void appendLine(StringWriter goldenMaster, String string) {
        goldenMaster.append(string);
        goldenMaster.append("\n");
    }
}
