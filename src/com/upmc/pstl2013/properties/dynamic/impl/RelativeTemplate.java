package com.upmc.pstl2013.properties.dynamic.impl;

import org.apache.log4j.Logger;
import com.upmc.pstl2013.alloyGenerator.jet.*;
import com.upmc.pstl2013.properties.*;
import com.upmc.pstl2013.properties.dynamic.AbstractStrategyDynamicBusiness;
import com.upmc.pstl2013.properties.dynamic.DynamicBusiness;

public class RelativeTemplate extends AbstractStrategyDynamicBusiness implements IJetTemplate {

  protected static String nl;
  public static synchronized RelativeTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    RelativeTemplate result = new RelativeTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "pred ";
  protected final String TEXT_2 = " {" + NL + "\t";
  protected final String TEXT_3 = "[";
  protected final String TEXT_4 = ",";
  protected final String TEXT_5 = "]" + NL + "}" + NL + "" + NL + "check {";
  protected final String TEXT_6 = "} for ";
  protected final String TEXT_7 = " State, ";
  protected final String TEXT_8 = " Object, ";
  protected final String TEXT_9 = " ActivityNode, ";
  protected final String TEXT_10 = " ActivityEdge expect 0";
  protected final String TEXT_11 = NL;

	@Override
	public String generate(Object argument) throws JetException
  {
    final StringBuffer stringBuffer = new StringBuffer();
     
	final Logger log = Logger.getLogger(RelativeTemplate.class);
	if (!(argument instanceof IProperties)) {
		final String error = "L'argument passé au template Jet n'est pas une IProperties.";
		log.error(error);
		throw new JetException(error);
	}

	final DynamicBusiness propertie = (DynamicBusiness) argument;
	final String predicatName = propertie.getString("predicatName");
	
	final String[] dataParams = propertie.getDataParams();
	final String noeud1 = dataParams[0];
	final String relation = dataParams[1];
	final String noeud2 = dataParams[2];
	
	final String inc = propertie.getString("nbState");
	final String nbObjects = propertie.getString("nbObjects");
	final String nbNodes = propertie.getString("nbNodes");
	final String nbEdges = propertie.getString("nbEdges");

    stringBuffer.append(TEXT_1);
    stringBuffer.append(predicatName);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(relation);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(noeud1);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(noeud2);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(predicatName);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(inc);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(nbObjects);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(nbNodes);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(nbEdges);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(TEXT_11);
    return stringBuffer.toString();
  }
}