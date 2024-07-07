package com.projet.eval.gno;
import java.lang.reflect.Field;
import java.sql.Connection;
/**
 *
 * @author Mirado
 */
public class Motherobj<T>
{ 
  public T create(Connection connection)throws Exception{
      GenericDao<T> gnDao=new GenericDao<T>();
      return gnDao.save(this, connection);
  }
  public T[] read(Connection connection)throws Exception{
    GenericDao<T> gnDao=new GenericDao<T>();
    T[] obj= gnDao.findAllT((T)this, connection);
    return obj;
  }
  public T[] read(Connection connection,int offset,int limit)throws Exception{
    GenericDao<T> gnDao=new GenericDao<T>();
    if(offset<0 || limit<0){
      offset=0;
      limit=0;
    }
    T[] obj= gnDao.findAllT((T)this, connection,offset,limit);
    return obj;
    //findAllT(T object,Connection connection,int offset,int limit)
  }
  public T readById(Connection connection)throws Exception{
      GenericDao<T> gnDao=new GenericDao<T>();
      T ob=gnDao.findT((T)this, connection);
      return ob;
  }
  public void update(Connection connection)throws Exception{
      GenericDao<T> gnDao=new GenericDao<T>();
      gnDao.update(this, connection);
  }
  public void delete(Connection connection)throws Exception{
      GenericDao<T> gnDao=new GenericDao<T>();
      gnDao.delete(this, connection);
  }

  public T[] readByQueryConvenable(Connection connection,String query)throws Exception{
    GenericDao<T> gnDao=new GenericDao<T>();
    T[] objs=gnDao.creerLstObjectsT((T)this,connection,query);
      return objs;
  }
  public T[] readByQueryConvenable(Connection connection,String sqlprepare,Object[] valueobjs)throws Exception{
    GenericDao<T> gnDao=new GenericDao<T>();
    T[] objs=gnDao.creerLstObjectsT((T)this, connection, sqlprepare, valueobjs);
      return objs;
  }
  public T[] readByQueryConvenable(Connection connection,String sqlprepare,Object[] valueobjs,int offset,int limit)throws Exception{
    GenericDao<T> gnDao=new GenericDao<T>();
    if(offset<0 || limit<0){
      offset=0;
      limit=0;
    }
    T[] objs=gnDao.creerLstObjectsT((T)this, connection, sqlprepare, valueobjs, offset, limit);
      return objs;
  }
  public T readOneByQueryConvenable(Connection connection,String query)throws Exception{
    T[] objs=readByQueryConvenable(connection, query);
    if(objs==null){return null;}
    return objs[0];
  }
  public T readOneByQueryConvenable(Connection connection,String sqlprepare,Object[] valuObjects)throws Exception{
    T[] objs=readByQueryConvenable(connection, sqlprepare, valuObjects);
    if(objs==null){return null;}
    return objs[0];
  }

  public void systemoutfield()throws Exception{
    Field[] fields=this.getClass().getDeclaredFields();
    System.out.println();
    for(int i=0;i<fields.length;i++){
      fields[i].setAccessible(true);
      System.out.print("("+fields[i].getType().getSimpleName()+")"+fields[i].getName()+"="+fields[i].get(this)+" | "); 
      fields[i].setAccessible(false);
    }
  }

}
