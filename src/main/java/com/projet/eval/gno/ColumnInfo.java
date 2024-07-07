package com.projet.eval.gno;
public class ColumnInfo {
    String name;
    boolean estPK;
    boolean estFK;
    String tabOriginFk;
    String colFromOrigin;
    String typecol;
 
    public ColumnInfo() {
    }
    public ColumnInfo(String name, boolean estPK, boolean estFK, String tabOriginFk, String colFromOrigin,String typecol) {
        this.name = name;
        this.estPK = estPK;
        this.estFK = estFK;
        this.tabOriginFk = tabOriginFk;
        this.colFromOrigin = colFromOrigin;
        this.typecol=typecol;
    }
 
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isEstPK() {
        return estPK;
    }
    public void setEstPK(boolean estPK) {
        this.estPK = estPK;
    }
    public boolean isEstFK() {
        return estFK;
    }
    public void setEstFK(boolean estFK) {
        this.estFK = estFK;
    }
    public String getTabOriginFk() {
        return tabOriginFk;
    }
    public void setTabOriginFk(String tabOriginFk) {
        this.tabOriginFk = tabOriginFk;
    }
    public String getColFromOrigin() {
        return colFromOrigin;
    }
    public void setColFromOrigin(String colFromOrigin) {
        this.colFromOrigin = colFromOrigin;
    }
    public String getTypecol() {
        return typecol;
    }
    public void setTypecol(String typecol) {
        this.typecol = typecol;
    }

  
}
