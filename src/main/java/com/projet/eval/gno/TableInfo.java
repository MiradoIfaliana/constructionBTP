package com.projet.eval.gno;
import java.util.List;
public class TableInfo {
    String tablename;
    List<ColumnInfo> lstcol;
    public TableInfo() {
    }
    public TableInfo(String tablename, List<ColumnInfo> lstcol) {
        this.tablename = tablename;
        this.lstcol = lstcol;
    }
    public String getTablename() {
        return tablename;
    }
    public void setTablename(String tablename) {
        this.tablename = tablename;
    }
    public List<ColumnInfo> getLstcol() {
        return lstcol;
    }
    public void setLstcol(List<ColumnInfo> lstcol) {
        this.lstcol = lstcol;
    }
    String name;
    boolean estPK; 
    boolean estFK;
    String tabOriginFk;
    String colFromOrigin;
    public void affiche(){
        System.out.println("TABLE : ------>"+tablename);
        for(int i=0;i<lstcol.size();i++){
            System.out.println("colonne :"+lstcol.get(i).getName()+" | colonneOrigin: "+lstcol.get(i).getColFromOrigin()+" | pk :"+lstcol.get(i).isEstPK()+" | fk :"+lstcol.get(i).isEstFK()+"  | table reference:"+lstcol.get(i).getTabOriginFk());
        }
    }
     
}
