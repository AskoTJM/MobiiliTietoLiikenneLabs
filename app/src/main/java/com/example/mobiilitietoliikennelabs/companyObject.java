package com.example.mobiilitietoliikennelabs;

class companyObject {
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyStockName() {
        return companyStockName;
    }

    public void setCompanyStockName(String companyStockName) {
        this.companyStockName = companyStockName;
    }

    public void setNames(String comName, String comStockName){
        this.companyName = comName;
        this.companyStockName = comStockName;
    }

    private String companyName;
    private String companyStockName;
}
