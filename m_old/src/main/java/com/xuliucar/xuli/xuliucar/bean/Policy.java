package com.xuliucar.xuli.xuliucar.bean;

/**
 * Created by skyward on 2016/7/21.
 *
 */
public class Policy {
    private String type_name;
    private String indemnity_norm;
    private String abatement;
    private String base;
    private String coefficient;
    private String total;

    public Policy(String type_name, String indemnity_norm, String abatement, String base, String coefficient, String total) {
        this.type_name = type_name;
        this.indemnity_norm = indemnity_norm;
        this.abatement = abatement;
        this.base = base;
        this.coefficient = coefficient;
        this.total = total;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getIndemnity_norm() {
        return indemnity_norm;
    }

    public void setIndemnity_norm(String indemnity_norm) {
        this.indemnity_norm = indemnity_norm;
    }

    public String getAbatement() {
        return abatement;
    }

    public void setAbatement(String abatement) {
        this.abatement = abatement;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(String coefficient) {
        this.coefficient = coefficient;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
