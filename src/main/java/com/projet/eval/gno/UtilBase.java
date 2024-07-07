package com.projet.eval.gno;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class UtilBase {
    public static String getTypeName(int sqlType) {
        String nametype = "";
        switch (sqlType) {
            case Types.INTEGER:
                nametype = "int";
                break;
            case Types.SMALLINT:
                nametype = "short";
                break;
            case Types.BIGINT:
                nametype = "long";
                break;
            case Types.NUMERIC:
                nametype = "BigDecimal";
                break;
            case Types.REAL:
                nametype = "float";
                break;
            case Types.DOUBLE:
            case Types.FLOAT:
                nametype = "double";
                break;
            case Types.CHAR:
            case Types.NCHAR:
            case Types.VARCHAR:
            case Types.NVARCHAR:
            case Types.LONGVARCHAR:
            case Types.LONGNVARCHAR:
            case Types.CLOB:
            case Types.NCLOB:
                nametype = "String";
                break;
            case Types.DATE:
                nametype="Date";
                break;
            case Types.TIME:
                nametype = "Time";
                break;
            case Types.TIMESTAMP:
                nametype = "LocalDateTime";
                break;
            case Types.BOOLEAN:
            case Types.BIT:
                nametype = "boolean";
                break;
            case Types.BINARY:
            case Types.VARBINARY:
            case Types.LONGVARBINARY:
            case Types.BLOB:
                nametype = "byte[]";
                break;
            default:
                nametype = "<Non géré>";
                break;
        }
        return nametype; 
    }
       public List<String> getPrimaryKeys(DatabaseMetaData metaData,String nametable)throws Exception{
        ResultSet pkeys= metaData.getPrimaryKeys(null, null, nametable);
        List<String> pkname=new ArrayList<String>();//colonnename //tablename //originname
        while (pkeys.next()) {
            pkname.add(pkeys.getString("COLUMN_NAME"));
            //pkeys.getInt("DATA_TYPE")
        }
        pkeys.close();
        return pkname;
    }

    public List<String[]> getForeignKeys(DatabaseMetaData metaData,String nametable)throws Exception{
        ResultSet fkeys = metaData.getImportedKeys(null, null, nametable);
        List<String[]> fkname=new ArrayList<String[]>();//tableoriginename //originname //etrangerename
        String[] temps=null;
        while (fkeys.next()) {
                temps=new String[3];
                temps[0]=fkeys.getString("PKTABLE_NAME");//table origine
                temps[1]=fkeys.getString("PKCOLUMN_NAME");//le vrai nom en tant que pk dans le table origin 
                temps[2]=fkeys.getString("FKCOLUMN_NAME");//le non en tant que entranger
                fkname.add(temps);
        }
        fkeys.close();
        return fkname;
    }

    public TableInfo getTableInfo( DatabaseMetaData metaData,String nametable)throws Exception{
        List<String> pkname=getPrimaryKeys(metaData, nametable);
        List<String[]> fkname=getForeignKeys(metaData,nametable);

        ResultSet columnsResultSet = metaData.getColumns(null, null, nametable, null);
        List<ColumnInfo> lstcol=new ArrayList<ColumnInfo>();
        ColumnInfo col=new ColumnInfo();
        while (columnsResultSet.next()) {
            col=new ColumnInfo();
            col.setName(columnsResultSet.getString("COLUMN_NAME"));// getTypeName(int sqlType)
            col.setTypecol( getTypeName(columnsResultSet.getInt("DATA_TYPE")) );
            //si c'est un pk
            for(int i=0;i<pkname.size();i++){
                if(col.getName().equals(pkname.get(i))==true){
                    col.setEstPK(true);
                    col.setEstFK(false);
                    i=pkname.size();//fin boucle
                }
            }
            //si c'est un fk
            for(int i=0;i<fkname.size();i++){
                if(col.getName().equals(fkname.get(i)[2])==true){
                    col.setEstFK(true);
                    col.setTabOriginFk(fkname.get(i)[0]);
                    col.setColFromOrigin(fkname.get(i)[1]);
                    i=fkname.size();//fin boucle
                }
            }
            lstcol.add(col);
        }
        columnsResultSet.close();
        TableInfo tabinfo=new TableInfo(nametable,lstcol);
        return tabinfo;
    } 
    public String[] getTableNameAll(Connection connection,String databasename ,String[] typeForGenere)throws Exception{//[0]=tablename,[1]=tabletype,[2]=tableschem
        DatabaseMetaData metaData=connection.getMetaData();
        if(typeForGenere==null){
            typeForGenere=new String[1];
            typeForGenere[0]="TABLE";
        }else{ for(int i=0;i<typeForGenere.length;i++){ typeForGenere[i]=typeForGenere[i].toUpperCase(); } }
        ResultSet table =metaData.getTables(databasename, "public", null, typeForGenere);
        //ResultSet table = metaData.getFunctions(this.databasename, "public", null); ---FUNCTION
        String[] tabsinfo=null;
        Vector<String> vTab=new Vector<String>(); 
        while (table.next()) {
            vTab.add(table.getString("TABLE_NAME"));
        }
        table.close();
        return vTab.toArray(new String[vTab.size()]);
    }
    public void executeNoSelect(Connection connection, String sqlprepare) throws Exception {
      try (PreparedStatement statement = connection.prepareStatement(sqlprepare)) {
          statement.executeUpdate();
          System.out.println(sqlprepare);
      }
    }
    public void resetData(Connection connection,String[] namestable)throws Exception{
        String sqlPrepTemp="";
        int k=0;
        int nbdelete=0;
        if(namestable!=null){
            while(k<namestable.length){ //essayon de supprimer 1 par 1 jusqu'a ce que tous sois supprimer 
                for(int i=0;i<namestable.length;i++){//car il y aura peut-etre l'exception du foreign key present quelque part
                    try{
                        System.out.println(namestable[i]);
                        sqlPrepTemp="delete from "+namestable[i];
                        executeNoSelect(connection, sqlPrepTemp);
                        nbdelete++;
                    }catch(SQLException sException){
                        sException.printStackTrace();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
                if(nbdelete==namestable.length){
                    k=namestable.length;
                }
                k++;
            }
        }
    }

}
