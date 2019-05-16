package com.tekzee.racp.ui.Form.weighting_machine.model;

import com.orm.SugarRecord;

public class DataWeighitngMachine extends SugarRecord {

    String dd;
    String mm;
    int mm_id;
    String yy;
    int YY_id;

    String proof;
    boolean physical_proof;

    String use;
    boolean usability;

    String aninal_type;
    int animal_type_id;

    String animal_sub_type;
    int animal_sub_type_id;

    String tagno_bakra;
    String age_bakra;
    String weight_bakra;

    String tagno_bakri;
    String age_bakri;
    String weight_bakri;

    String tagno_nar;
    String age_nar;
    String weight_nar;

    String tagno_mada;
    String age_mada;
    String weight_mada;

    String parent_bakre_ka_tag_no;
    String parent_bakre_ka_bhar;
    String parent_bakre_ki_age;

    String parent_bakri_ka_tag_no;
    String parent_bakri_ka_bhar;
    String parent_bakri_ki_age;




    public DataWeighitngMachine() {
    }

    public DataWeighitngMachine(String dd, String mm, int mm_id, String yy, int YY_id, String proof, boolean physical_proof, String use, boolean usability, String aninal_type, int animal_type_id, String animal_sub_type, int animal_sub_type_id, String tagno_bakra, String age_bakra, String weight_bakra, String tagno_bakri, String age_bakri, String weight_bakri, String tagno_nar, String age_nar, String weight_nar, String tagno_mada, String age_mada, String weight_mada, String parent_bakre_ka_tag_no, String parent_bakre_ka_bhar, String parent_bakre_ki_age, String parent_bakri_ka_tag_no, String parent_bakri_ka_bhar, String parent_bakri_ki_age) {
        this.dd = dd;
        this.mm = mm;
        this.mm_id = mm_id;
        this.yy = yy;
        this.YY_id = YY_id;
        this.proof = proof;
        this.physical_proof = physical_proof;
        this.use = use;
        this.usability = usability;
        this.aninal_type = aninal_type;
        this.animal_type_id = animal_type_id;
        this.animal_sub_type = animal_sub_type;
        this.animal_sub_type_id = animal_sub_type_id;
        this.tagno_bakra = tagno_bakra;
        this.age_bakra = age_bakra;
        this.weight_bakra = weight_bakra;
        this.tagno_bakri = tagno_bakri;
        this.age_bakri = age_bakri;
        this.weight_bakri = weight_bakri;
        this.tagno_nar = tagno_nar;
        this.age_nar = age_nar;
        this.weight_nar = weight_nar;
        this.tagno_mada = tagno_mada;
        this.age_mada = age_mada;
        this.weight_mada = weight_mada;
        this.parent_bakre_ka_tag_no = parent_bakre_ka_tag_no;
        this.parent_bakre_ka_bhar = parent_bakre_ka_bhar;
        this.parent_bakre_ki_age = parent_bakre_ki_age;
        this.parent_bakri_ka_tag_no = parent_bakri_ka_tag_no;
        this.parent_bakri_ka_bhar = parent_bakri_ka_bhar;
        this.parent_bakri_ki_age = parent_bakri_ki_age;
    }


    public String getParent_bakre_ka_tag_no() {
        return parent_bakre_ka_tag_no;
    }

    public String getParent_bakre_ka_bhar() {
        return parent_bakre_ka_bhar;
    }

    public String getParent_bakre_ki_age() {
        return parent_bakre_ki_age;
    }

    public String getParent_bakri_ka_tag_no() {
        return parent_bakri_ka_tag_no;
    }

    public String getParent_bakri_ka_bhar() {
        return parent_bakri_ka_bhar;
    }

    public String getParent_bakri_ki_age() {
        return parent_bakri_ki_age;
    }

    public String getDd() {
        return dd;
    }

    public String getMm() {
        return mm;
    }

    public int getMm_id() {
        return mm_id;
    }

    public String getYy() {
        return yy;
    }

    public int getYY_id() {
        return YY_id;
    }

    public String getProof() {
        return proof;
    }

    public boolean isPhysical_proof() {
        return physical_proof;
    }

    public String getUse() {
        return use;
    }

    public boolean isUsability() {
        return usability;
    }

    public String getAninal_type() {
        return aninal_type;
    }

    public int getAnimal_type_id() {
        return animal_type_id;
    }

    public String getAnimal_sub_type() {
        return animal_sub_type;
    }

    public int getAnimal_sub_type_id() {
        return animal_sub_type_id;
    }

    public String getTagno_bakra() {
        return tagno_bakra;
    }

    public String getAge_bakra() {
        return age_bakra;
    }

    public String getWeight_bakra() {
        return weight_bakra;
    }

    public String getTagno_bakri() {
        return tagno_bakri;
    }

    public String getAge_bakri() {
        return age_bakri;
    }

    public String getWeight_bakri() {
        return weight_bakri;
    }

    public String getTagno_nar() {
        return tagno_nar;
    }

    public String getAge_nar() {
        return age_nar;
    }

    public String getWeight_nar() {
        return weight_nar;
    }

    public String getTagno_mada() {
        return tagno_mada;
    }

    public String getAge_mada() {
        return age_mada;
    }

    public String getWeight_mada() {
        return weight_mada;
    }
}
