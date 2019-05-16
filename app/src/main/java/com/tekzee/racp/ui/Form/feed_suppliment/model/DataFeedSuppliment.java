package com.tekzee.racp.ui.Form.feed_suppliment.model;

import com.orm.SugarRecord;

import java.io.StringReader;

public class DataFeedSuppliment extends SugarRecord {

    String dd;
    String mm;
    int mm_id;
    String yy;
    int yy_id;
    String animal_type;
    int animal_type_id;

    String tag_no;

    String start_age;
    String start_weight;
    String start_milk_producion;
    String start_amount_feedsuppliment;
    String start_date;
    String date_90day;

    String weight_1;
    String coat_shinning_1;
    String heat_friquency_1;
    String milk_production_1;

    String weight_2;
    String coat_shinning_2;
    String heat_friquency_2;
    String milk_production_2;

    String weight_3;
    String coat_shinning_3;
    String heat_friquency_3;
    String milk_production_3;


    public DataFeedSuppliment() {
    }

    public DataFeedSuppliment(String dd, String mm, int mm_id, String yy, int yy_id, String animal_type, int animal_type_id, String tag_no, String start_age, String start_weight, String start_milk_producion, String start_amount_feedsuppliment, String start_date, String date_90day, String weight_1, String coat_shinning_1, String heat_friquency_1, String milk_production_1, String weight_2, String coat_shinning_2, String heat_friquency_2, String milk_production_2, String weight_3, String coat_shinning_3, String heat_friquency_3, String milk_production_3) {
        this.dd = dd;
        this.mm = mm;
        this.mm_id = mm_id;
        this.yy = yy;
        this.yy_id = yy_id;
        this.animal_type = animal_type;
        this.animal_type_id = animal_type_id;
        this.tag_no = tag_no;
        this.start_age = start_age;
        this.start_weight = start_weight;
        this.start_milk_producion = start_milk_producion;
        this.start_amount_feedsuppliment = start_amount_feedsuppliment;
        this.start_date = start_date;
        this.date_90day = date_90day;
        this.weight_1 = weight_1;
        this.coat_shinning_1 = coat_shinning_1;
        this.heat_friquency_1 = heat_friquency_1;
        this.milk_production_1 = milk_production_1;
        this.weight_2 = weight_2;
        this.coat_shinning_2 = coat_shinning_2;
        this.heat_friquency_2 = heat_friquency_2;
        this.milk_production_2 = milk_production_2;
        this.weight_3 = weight_3;
        this.coat_shinning_3 = coat_shinning_3;
        this.heat_friquency_3 = heat_friquency_3;
        this.milk_production_3 = milk_production_3;
    }

    public String getDd() {
        return dd;
    }

    public String getMm() {
        return mm;
    }

    public String getStart_milk_producion() {
        return start_milk_producion;
    }

    public int getMm_id() {
        return mm_id;
    }

    public String getYy() {
        return yy;
    }

    public String getTag_no() {
        return tag_no;
    }

    public int getYy_id() {
        return yy_id;
    }

    public String getAnimal_type() {
        return animal_type;
    }

    public int getAnimal_type_id() {
        return animal_type_id;
    }

    public String getStart_age() {
        return start_age;
    }

    public String getStart_weight() {
        return start_weight;
    }

    public String getStart_amount_feedsuppliment() {
        return start_amount_feedsuppliment;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getDate_90day() {
        return date_90day;
    }

    public String getWeight_1() {
        return weight_1;
    }

    public String getCoat_shinning_1() {
        return coat_shinning_1;
    }

    public String getHeat_friquency_1() {
        return heat_friquency_1;
    }

    public String getMilk_production_1() {
        return milk_production_1;
    }

    public String getWeight_2() {
        return weight_2;
    }

    public String getCoat_shinning_2() {
        return coat_shinning_2;
    }

    public String getHeat_friquency_2() {
        return heat_friquency_2;
    }

    public String getMilk_production_2() {
        return milk_production_2;
    }

    public String getWeight_3() {
        return weight_3;
    }

    public String getCoat_shinning_3() {
        return coat_shinning_3;
    }

    public String getHeat_friquency_3() {
        return heat_friquency_3;
    }

    public String getMilk_production_3() {
        return milk_production_3;
    }
}
