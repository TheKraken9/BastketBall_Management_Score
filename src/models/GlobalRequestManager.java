package models;

import connecting.GlobalConnection;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.Vector;

public class GlobalRequestManager {
    Statement stmt;
    String request;
    Class cls;
    ResultSet rs;

    public GlobalRequestManager() {
        cls = this.getClass();
    }

    /*public GlobalRequestManager[] historiser(GlobalRequestManager filter, Connection co, String action) throws SQLException {
        try {
            Field[] getAttribute = this.getClass().getDeclaredFields();
            request = sqlselectgenerator(filter);
            System.out.println(request);
            Class clses = this.getClass();
            Class[] parameters = getParam(getAttribute);
            Constructor cstr = clses.getConstructor(parameters);
            Vector<GlobalRequestManager> result = new Vector<GlobalRequestManager>();
            stmt = co.createStatement();
            rs = stmt.executeQuery(request);
            ResultSetMetaData rmtdt = rs.getMetaData();
            String resultatHist = "";
            String[] attributes = getAttribute(cls);
            String tableName = getTableName(this);
            while (rs.next()) {
                Object[] obj = new Object[parameters.length];
                for (int i = 0; i < rmtdt.getColumnCount(); i++) {
                    obj[i] = rs.getObject(i+1);
                    resultatHist += "" + attributes[i] + ":" + obj[i] + "; ";
                }

                Method[] getter = accessmethods1(cls);
                String[] strs = new String[getter.length];
                for (int i = 0; i < strs.length; i++) {
                    strs[i] = getter[i].getName();
                }

                String[] values = getValues(getter);
                String attribute = setString(attributes, false);
                String value = setString(values, true);
                String sql = "insert into historique(id, tableName, action, datehist, valeur) values (seqhist.nextval,'" + tableName + "','" + action + "',current_timestamp,'" + resultatHist +"')";

                Statement stmte = co.createStatement();
                ResultSet res = stmte.executeQuery(sql);

                Object[] typed = castParameter(obj, parameters);
                result.add((GlobalRequestManager) cstr.newInstance(typed));
            }
            GlobalRequestManager[] object = convertDb(result);
            return object;
        } catch (Exception e) {
            co.rollback();
            e.printStackTrace();
        } finally {
            stmt.close();
            rs.close();
        }
        return null;
    }*/

    public GlobalRequestManager[] find(GlobalRequestManager filter, Connection co) throws SQLException {
        try {
            Field[] getAttribute = this.getClass().getDeclaredFields();
            request = sqlselectgenerator(filter);
            System.out.println(request);
            Class clses = this.getClass();
            Class[] parameters = getParam(getAttribute);
            Constructor cstr = clses.getConstructor(parameters);
            Vector<GlobalRequestManager> result = new Vector<GlobalRequestManager>();
            stmt = co.createStatement();
            rs = stmt.executeQuery(request);
            ResultSetMetaData rmtdt = rs.getMetaData();
            while (rs.next()) {
                Object[] obj = new Object[parameters.length];
                for (int i = 0; i < rmtdt.getColumnCount(); i++) {
                    obj[i] = rs.getObject(i + 1);
                    System.out.println(obj[i]);
                }
                Object[] typed = castParameter(obj, parameters);
                result.add((GlobalRequestManager) cstr.newInstance(typed));
            }
            GlobalRequestManager[] object = convertDb(result);
            return object;
        } catch (Exception e) {
            //co.rollback();
            e.printStackTrace();
        } finally {
            //stmt.close();
            //rs.close();
        }
        return null;
    }

    public void update(Connection co) throws SQLException {
        try {
            stmt = co.createStatement();
            request = sqlupdategenerator();
            stmt.executeUpdate(request);
            //co.commit();
        } catch (Exception e) {
            //co.rollback();
            e.printStackTrace();
        } finally {
            //stmt.close();
        }
    }

    public void insert(Connection co) throws SQLException {
        try {
            stmt = co.createStatement();
            request = sqlinsertgenerator();
            stmt.executeUpdate(request);
            //co.commit();
        } catch (Exception e) {
            //co.rollback();
            e.printStackTrace();
        } finally {
            //stmt.close();
        }
    }

    public void delete(Connection co) throws SQLException {
        try {
            stmt = co.createStatement();
            request = sqldeletegenerator();
            stmt.executeUpdate(request);
            //co.commit();
        } catch (Exception e) {
            //co.rollback();
            e.printStackTrace();
        } finally {
            //stmt.close();
        }
    }

    //for SELECT REQUEST
    public String sqlselectgenerator(GlobalRequestManager filter) throws Exception {
        String sql;
        String tableName = getTableName(this);
        String[] criterionName = getCriterion(filter);
        String[] criterionValue = getCriterionValue(filter);
        String[] attribute = getAttr(this.getClass());
        String attr = setString(attribute, false);
        if (criterionValue[0] == "null") {
            sql = "select " + attr + " from " + tableName;
        } else {
            String conditions = getConditions(criterionName, criterionValue);
            sql = "select " + attr + " from " + tableName + " where " + conditions;
        }
        System.out.println(sql);
        return sql;
    }

    //for UPDATE REQUEST
    public String sqlupdategenerator() throws Exception {
        Method[] getter = accessmethods1(cls);
        String[] strs = new String[getter.length];
        for (int i = 0; i < strs.length; i++) {
            strs[i] = getter[i].getName();
        }
        String[] attributes = getAttribute(cls);
        String[] values = getValues(getter);
        String tableName = getTableName(this);
        String attribute = setString(attributes, false);
        String value = setString(values, true);
        String sql = "update " + tableName + " set " + updateValue(attributes, values);
        System.out.println(sql);
        return sql;
    }

    //for INSERT REQUEST
    public String sqlinsertgenerator() throws Exception {
        Method[] getter = accessmethods1(cls);
        String[] strs = new String[getter.length];
        for (int i = 0; i < strs.length; i++) {
            strs[i] = getter[i].getName();
        }
        String[] attributes = getAttribute(cls);
        String[] values = getValues(getter);
        String tableName = getTableName(this);
        String attribute = setString(attributes, false);
        String value = setString(values, true);
        String sql = "insert into " + tableName + " (" + attribute + ") values (" + value + ")";
        System.out.println(sql);
        return sql;
    }

    //for DELETE REQUEST
    public String sqldeletegenerator() throws Exception {
        Method[] getter = accessmethods1(cls);
        String[] strs = new String[getter.length];
        for (int i = 0; i < getter.length; i++) {
            strs[i] = getter[i].getName();
        }
        String[] attibutes = getAttribute(cls);
        String[] values = getValues(getter);
        String tableName = getTableName(this);
        String attribute = attibutes[0];
        String value = values[0];

        String sql = "delete " + tableName + " where " + attribute + "='" + value + "'";
        System.out.println(sql);
        return sql;
    }

    public Method[] accessmethods1(Class cls) {
        Field[] field = cls.getDeclaredFields();
        String[] attribute = new String[countfield(field)];
        for (int i = 0; i < field.length; i++) {
            attribute[i] = field[i].getName();
        }
        Method[] getters = findgetters(attribute, cls);
        return getters;
    }

    private Method[] findgetters(String[] attribute, Class cls) {
        Method[] allmethods = cls.getMethods();
        Method[] getters = new Method[attribute.length];
        int index = 0;
        for (int k = 0; k < attribute.length; k++) {
            for (int i = 0; i < allmethods.length; i++) {
                String str = upperCase(attribute[k]);
                if (allmethods[i].getName().contains("get") && allmethods[i].getName().contains(str)) {
                    getters[index] = allmethods[i];
                    index++;
                }
            }
        }
        return getters;
    }

    //more utilities
    private GlobalRequestManager[] convertDb(Vector<GlobalRequestManager> liste) {
        GlobalRequestManager[] retour = new GlobalRequestManager[liste.size()];
        for (int i = 0; i < liste.size(); i++) {
            retour[i] = (GlobalRequestManager) liste.elementAt(i);
        }
        return retour;
    }

    private String[] getCriterion(GlobalRequestManager grm) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<? extends GlobalRequestManager> cls = grm.getClass();
        Method[] getters = accessmethods1(cls);
        Field[] attribute = cls.getDeclaredFields();
        Vector<String> column = new Vector<String>();
        for (int i = 0; i < getters.length; i++) {
            Object obj = getters[i].invoke(grm);
            if (obj != null) {
                //System.out.println(getters[i].getName()+" used as filter");
                column.addElement(attribute[i].getName());
                //System.out.println(attribute[i].getName());
            }
        }
        String[] retour = new String[column.size()];
        for (int i = 0; i < column.size(); i++) {
            retour[i] = column.elementAt(i).toString();
        }
        return retour;
    }

    private String[] getCriterionValue(GlobalRequestManager grm) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<? extends GlobalRequestManager> cls = grm.getClass();
        Method[] getters = accessmethods1(cls);
        Field[] attribute = cls.getDeclaredFields();
        Vector<String> values = new Vector<String>();
        for (int i = 0; i < getters.length; i++) {
            System.out.println(getters[i].invoke(grm));
            if (getters[i].invoke(grm) == (null)) {
                continue;
            }
            /*if(getters[i].invoke(grm).equals(0)) {

            }*/
            values.addElement(getters[i].invoke(grm).toString());
        }
        String[] retour = new String[values.size()];
        if (values.size() == 0) {
            String[] inter = new String[1];
            inter[0] = "null";
            return inter;
        }
        for (int i = 0; i < values.size(); i++) {
            retour[i] = values.elementAt(i).toString();
        }
        return retour;
    }

    private String[] getAttr(Class clss) {
        Field[] field = clss.getDeclaredFields();
        String[] attribute = new String[countfield(field)];
        int index = 0;
        for (int i = 0; i < field.length; i++) {
            attribute[index] = field[i].getName();
            index++;
        }
        return attribute;
    }

    private String getConditions(String[] column, String[] values) {
        String retour = "";
        for (int i = 0; i < column.length; i++) {
            if (i + 1 != column.length) {
                retour += column[i] + "= '" + values[i] + "' and ";
            } else {
                retour += column[i] + "='" + values[i] + "'";
            }
        }
        return retour;
    }

    private Class[] getParam(Field[] attr) {
        Class[] retour = new Class[attr.length];
        for (int i = 0; i < attr.length; i++) {
            retour[i] = attr[i].getType();
        }
        return retour;
    }

    private Object[] castParameter(Object[] parameter, Class[] parameterType) {
        Object[] retour = new Object[parameter.length];
        for (int i = 0; i < retour.length; i++) {
            retour[i] = parameterType[i].cast(parameter[i]);
        }
        return retour;
    }

    private String updateValue(String[] attribute, String[] value) {
        String retour = "";
        int count = 0;
        for (int i = 1; i < attribute.length; i++) {
            if (value[i] != null) {
                count++;
                if (i + 1 != attribute.length) {
                    retour += attribute[i] + "='" + value[i] + "',";
                    //if (count != 1) retour += ",";
                } else {
                    retour += attribute[i] + "='" + value[i] + "'";
                }
            }
        }
        retour += " WHERE " + attribute[0] + "='" + value[0] + "'";
        return retour;
    }

    private String getTableName(GlobalRequestManager caller) {
        Class clss = caller.getClass();
        String[] classname = clss.getName().split("\\.");
        System.out.println(clss.getName());
        String table = classname[classname.length - 1];
        System.out.println(table);
        return table;
    }

    private String upperCase(String str) {
        char[] chars = str.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        String retour = String.valueOf(chars);
        return retour;
    }

    public Method[] accessmethods(Class cls) {
        Field[] field = cls.getDeclaredFields();
        String[] attribute = new String[countfield(field)];
        int index = 0;
        for (int i = 0; i < field.length; i++) {
            attribute[index] = field[i].getName();
            index++;
        }
        Method[] getters = findgetters(attribute, cls);
        return getters;
    }

    private int countfield(Field[] field) {
        int retour = 0;
        for (int i = 0; i < field.length; i++) {
            retour++;
        }
        return retour;
    }

    private String[] getAttribute(Class cls) {
        Field[] field = cls.getDeclaredFields();
        String[] attribute = new String[countfield(field)];
        int index = 0;
        for (int i = 0; i < field.length; i++) {
            attribute[index] = field[i].getName();
            index++;
        }
        return attribute;
    }

    private String[] getValues(Method[] getters) throws Exception {
        Object[] obj_result = new Object[getters.length];
        for (int i = 0; i < getters.length; i++) obj_result[i] = getters[i].invoke(this);
        String[] finalresult = castString(obj_result);
        return finalresult;
    }

    private String[] castString(Object[] obj) {
        String[] retour = new String[obj.length];
        System.out.println(retour.length);
        for (int i = 0; i < obj.length; i++) {
            System.out.println(obj[i]);
            if (obj[i] == null) {
                System.out.println(obj[i]);
                continue;
            }
            retour[i] = obj[i].toString();
            System.out.println("ito");
            System.out.println(retour[i]);
        }
        return retour;
    }

    private String setString(String[] stringArray, boolean quote) {
        String retour = "";
        if (quote) {
            for (int i = 0; i < stringArray.length; i++)
                if (stringArray.length != i + 1) retour += "'" + stringArray[i] + "',";
                else retour += "'" + stringArray[i] + "'";
        } else {
            for (int i = 0; i < stringArray.length; i++) {
                if (i + 1 != stringArray.length) retour += stringArray[i] + ",";
                else retour += stringArray[i];
            }
        }
        return retour;
    }

    /*private int countConditions( GlobalRequestManager grm ) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class cls = grm.getClass();
        Method [] getters = accessmethods1(cls) ;
        int retour = 0 ;
        for (int i = 0; i < getters.length; i++) {
            if(getters[i].invoke(grm)!=null ) {
                retour++;
            }
        }
        return retour;
    }*/
}

