package com.tekzee.racp.ui.Form.vitrit_bakro_kavivran.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data1 {
    @SerializedName("bakra_data")
    @Expose
    private BakraData bakraData;
    @SerializedName("bakri_data")
    @Expose
    private List <BakriDatum> bakriData = null;
    @SerializedName("physical_proof_data")
    @Expose
    private PhysicalProofData physicalProofData;

    /**
     * No args constructor for use in serialization
     */
    public Data1() {
    }

    /**
     * @param bakriData
     * @param physicalProofData
     * @param bakraData
     */
    public Data1(BakraData bakraData, List <BakriDatum> bakriData, PhysicalProofData physicalProofData) {
        super();
        this.bakraData = bakraData;
        this.bakriData = bakriData;
        this.physicalProofData = physicalProofData;
    }

    public BakraData getBakraData() {
        return bakraData;
    }

    public void setBakraData(BakraData bakraData) {
        this.bakraData = bakraData;
    }

    public List <BakriDatum> getBakriData() {
        return bakriData;
    }

    public void setBakriData(List <BakriDatum> bakriData) {
        this.bakriData = bakriData;
    }

    public PhysicalProofData getPhysicalProofData() {
        return physicalProofData;
    }

    public void setPhysicalProofData(PhysicalProofData physicalProofData) {
        this.physicalProofData = physicalProofData;
    }

}
