
package com.tekzee.racp.ui.Form.weighting_machine.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

@SerializedName("table_id")
@Expose
private Integer tableId;
@SerializedName("animaltype_id")
@Expose
private Integer animaltypeId;
@SerializedName("physical_proof")
@Expose
private String physicalProof;
@SerializedName("usability")
@Expose
private String usability;
@SerializedName("bakra_tag_no")
@Expose
private Integer bakraTagNo;
@SerializedName("bakra_bhar")
@Expose
private Integer bakraBhar;
@SerializedName("bakra_age")
@Expose
private Integer bakraAge;
@SerializedName("bakri_tag_no")
@Expose
private Integer bakriTagNo;
@SerializedName("bakri_bhar")
@Expose
private Object bakriBhar;
@SerializedName("bakri_age")
@Expose
private Integer bakriAge;
@SerializedName("nar_tag_no")
@Expose
private Integer narTagNo;
@SerializedName("nar_bhar")
@Expose
private Object narBhar;
@SerializedName("nar_age")
@Expose
private Integer narAge;
@SerializedName("mada_tag_no")
@Expose
private Integer madaTagNo;
@SerializedName("mada_bhar")
@Expose
private Object madaBhar;
@SerializedName("mada_age")
@Expose
private Integer madaAge;
@SerializedName("parent_bakre_ka_tag_no")
@Expose
private Integer parentBakreKaTagNo;
@SerializedName("parent_bakre_ka_bhar")
@Expose
private Integer parentBakreKaBhar;
@SerializedName("parent_bakre_ki_age")
@Expose
private Integer parentBakreKiAge;
@SerializedName("parent_bakri_ka_tag_no")
@Expose
private Integer parentBakriKaTagNo;
@SerializedName("parent_bakri_ka_bhar")
@Expose
private Object parentBakriKaBhar;
@SerializedName("parent_bakri_ki_age")
@Expose
private Integer parentBakriKiAge;
@SerializedName("date_receipt")
@Expose
private String dateReceipt;
@SerializedName("form_id")
@Expose
private Integer formId;

/**
* No args constructor for use in serialization
*
*/


public Data() {
}
    /**
     *
     * @param parentBakreKaBhar
     * @param madaAge
     * @param narAge
     * @param parentBakreKaTagNo
     * @param bakriBhar
     * @param parentBakreKiAge
     * @param dateReceipt
     * @param narBhar
     * @param parentBakriKaTagNo
     * @param parentBakriKiAge
     * @param madaTagNo
     * @param usability
     * @param parentBakriKaBhar
     * @param tableId
     * @param formId
     * @param bakraBhar
     * @param bakriTagNo
     * @param bakriAge
     * @param bakraTagNo
     * @param bakraAge
     * @param narTagNo
     * @param animaltypeId
     * @param madaBhar
     * @param physicalProof
     */

    public Data(Integer tableId, Integer animaltypeId, String physicalProof, String usability, Integer bakraTagNo, Integer bakraBhar, Integer bakraAge, Integer bakriTagNo, Object bakriBhar, Integer bakriAge, Integer narTagNo, Object narBhar, Integer narAge, Integer madaTagNo, Object madaBhar, Integer madaAge, Integer parentBakreKaTagNo, Integer parentBakreKaBhar, Integer parentBakreKiAge, Integer parentBakriKaTagNo, Object parentBakriKaBhar, Integer parentBakriKiAge, String dateReceipt, Integer formId) {
        this.tableId = tableId;
        this.animaltypeId = animaltypeId;
        this.physicalProof = physicalProof;
        this.usability = usability;
        this.bakraTagNo = bakraTagNo;
        this.bakraBhar = bakraBhar;
        this.bakraAge = bakraAge;
        this.bakriTagNo = bakriTagNo;
        this.bakriBhar = bakriBhar;
        this.bakriAge = bakriAge;
        this.narTagNo = narTagNo;
        this.narBhar = narBhar;
        this.narAge = narAge;
        this.madaTagNo = madaTagNo;
        this.madaBhar = madaBhar;
        this.madaAge = madaAge;
        this.parentBakreKaTagNo = parentBakreKaTagNo;
        this.parentBakreKaBhar = parentBakreKaBhar;
        this.parentBakreKiAge = parentBakreKiAge;
        this.parentBakriKaTagNo = parentBakriKaTagNo;
        this.parentBakriKaBhar = parentBakriKaBhar;
        this.parentBakriKiAge = parentBakriKiAge;
        this.dateReceipt = dateReceipt;
        this.formId = formId;
    }


    public Integer getTableId() {
        return tableId;
    }

    public Integer getAnimaltypeId() {
        return animaltypeId;
    }

    public String getPhysicalProof() {
        return physicalProof;
    }

    public String getUsability() {
        return usability;
    }

    public Integer getBakraTagNo() {
        return bakraTagNo;
    }

    public Integer getBakraBhar() {
        return bakraBhar;
    }

    public Integer getBakraAge() {
        return bakraAge;
    }

    public Integer getBakriTagNo() {
        return bakriTagNo;
    }

    public Object getBakriBhar() {
        return bakriBhar;
    }

    public Integer getBakriAge() {
        return bakriAge;
    }

    public Integer getNarTagNo() {
        return narTagNo;
    }

    public Object getNarBhar() {
        return narBhar;
    }

    public Integer getNarAge() {
        return narAge;
    }

    public Integer getMadaTagNo() {
        return madaTagNo;
    }

    public Object getMadaBhar() {
        return madaBhar;
    }

    public Integer getMadaAge() {
        return madaAge;
    }

    public Integer getParentBakreKaTagNo() {
        return parentBakreKaTagNo;
    }

    public Integer getParentBakreKaBhar() {
        return parentBakreKaBhar;
    }

    public Integer getParentBakreKiAge() {
        return parentBakreKiAge;
    }

    public Integer getParentBakriKaTagNo() {
        return parentBakriKaTagNo;
    }

    public Object getParentBakriKaBhar() {
        return parentBakriKaBhar;
    }

    public Integer getParentBakriKiAge() {
        return parentBakriKiAge;
    }

    public String getDateReceipt() {
        return dateReceipt;
    }

    public Integer getFormId() {
        return formId;
    }
}