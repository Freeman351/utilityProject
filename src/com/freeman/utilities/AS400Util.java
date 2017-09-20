package com.freeman.utilities;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.freeman.exception.BaseException;
import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.AS400Text;
import com.ibm.as400.access.ErrorCompletingRequestException;
import com.ibm.as400.access.ObjectDoesNotExistException;
import com.ibm.as400.access.ProgramCall;
import com.ibm.as400.access.ProgramParameter;

public class AS400Util implements Serializable {

	private static Logger log = Logger.getLogger(AS400Util.class);

	public static void processOrderViaCLP(String orderNumber)
			throws BaseException {

		// +--------------------------------------------------------------------+
		// | Create an AS400 object for the iSeries server. |
		// +--------------------------------------------------------------------+
		Properties prop = (new PropertyManager()).loadAs400Properties();
		// Create an AS/400 connection
		String env = (new PropertyManager()).loadEnvProperties();
		String suffix = env.equals("P") ? "_P" : "";

		String AS400IPAddr = prop.getProperty("AS400_Server_Name" + suffix);
		String DATABASE_USER_NAME = prop.getProperty("user" + suffix);
		String DATABASE_PASSWORD = prop.getProperty("password" + suffix);
		String libraryNameForCLP = prop.getProperty("CLlibraryName" + suffix);
		String programNameForCLP = prop.getProperty("CLprogramName" + suffix);
		String paramForCLP = orderNumber;

		log.info("AS400IPAddr=" + AS400IPAddr + ";DATABASE_USER_NAME="
				+ DATABASE_USER_NAME + ";libraryNameForCLP="
				+ libraryNameForCLP + ";paramForCLP=" + paramForCLP
				+ ";programNameForCLP=" + programNameForCLP);

		AS400 system = new AS400(AS400IPAddr, DATABASE_USER_NAME,
				DATABASE_PASSWORD);
		ProgramCall program = new ProgramCall(system);

		// Initialize the name of the program to run.
		String programName = "/QSYS.LIB/" + libraryNameForCLP.trim() + ".LIB/"
				+ programNameForCLP.trim() + ".PGM";

		// Set up the 1 parameters - the unique web order number
		ProgramParameter[] parameterList = new ProgramParameter[1];

		// (1) Web Order Number - INPUT (String - 22)
		AS400Text tcChar = new AS400Text(22, system);
		parameterList[0] = new ProgramParameter(tcChar.toBytes(paramForCLP));
		// Set the program name and parameter list.
		try {
			program.setProgram(programName, parameterList);
		} catch (java.beans.PropertyVetoException e) {
		}
		// Run the program.
		try {
			if (program.run() != true) {
				// Report failure onprogram call
				log.info("AS400 Order Processing CLP fail");
			} else {
				log.info("AS400 Order Processing CLP done");
			}
		} catch (AS400SecurityException e) {
			log.error("CLP Call - AS400SecurityException: " + e.getMessage());
			throw new BaseException("CLP Call - AS400SecurityException: "
					+ e.getMessage());
		} catch (ErrorCompletingRequestException e) {
			log.error("CLP call - ErrorCompletingRequestException: "
					+ e.getMessage());
			throw new BaseException(
					"CLP call - ErrorCompletingRequestException: "
							+ e.getMessage());
		} catch (IOException e) {
			log.error("CLP call - IOException: " + e.getMessage());
			throw new BaseException("CLP call - IOException: " + e.getMessage());
		} catch (InterruptedException e) {
			log.error("CLP call - InterruptedException: " + e.getMessage());
			throw new BaseException("CLP call - InterruptedException: "
					+ e.getMessage());
		} catch (ObjectDoesNotExistException e) {
			log.error("CLP call - ObjectDoesNotExistException: "
					+ e.getMessage());
			throw new BaseException("CLP call - ObjectDoesNotExistException: "
					+ e.getMessage());
		} finally {
			try {
				// Done with the system.
				system.disconnectAllServices();
			} catch (Exception ex) {
				log.error(ex.getMessage());
			}
		}
		return;
	}

	public static void main(String[] args) {
		String AS400IPAddr = "10.64.19.48";
		String DATABASE_USER_NAME = "ext1";
		String DATABASE_PASSWORD = "F57M1HHB";
		String libraryNameForCLP = "ZPGMLIB0";
		String programNameForCLP = "SFC685";
		String paramForCLP = "W803921";

		System.out.println("AS400IPAddr=" + AS400IPAddr
				+ ";DATABASE_USER_NAME=" + DATABASE_USER_NAME
				+ ";libraryNameForCLP=" + libraryNameForCLP + ";paramForCLP="
				+ paramForCLP + ";programNameForCLP=" + programNameForCLP);

		AS400 system = new AS400(AS400IPAddr, DATABASE_USER_NAME,
				DATABASE_PASSWORD);
		ProgramCall program = new ProgramCall(system);

		// Initialize the name of the program to run.
		String programName = "/QSYS.LIB/" + libraryNameForCLP.trim() + ".LIB/"
				+ programNameForCLP.trim() + ".PGM";
		System.out.println(programName);

		// Set up the 1 parameters - the unique web order number
		ProgramParameter[] parameterList = new ProgramParameter[1];

		// (1) Web Order Number - INPUT (String - 18)
		AS400Text tcChar = new AS400Text(22, system);
		parameterList[0] = new ProgramParameter(tcChar.toBytes(paramForCLP));
		// Set the program name and parameter list.
		try {
			program.setProgram(programName, parameterList);
		} catch (java.beans.PropertyVetoException e) {
		}
		// Run the program.
		try {
			if (program.run() != true) {
				// Report failure onprogram call
				System.out.println("AS400 Order Processing CLP fail");
			} else {
				System.out.println("AS400 Order Processing CLP done");
			}
		} catch (AS400SecurityException e) {
			System.out.println("CLP Call - AS400SecurityException: "
					+ e.getMessage());
		} catch (ErrorCompletingRequestException e) {
			System.out.println("CLP call - ErrorCompletingRequestException: "
					+ e.getMessage());
		} catch (IOException e) {
			System.out.println("CLP call - IOException: " + e.getMessage());
		} catch (InterruptedException e) {
			System.out.println("CLP call - InterruptedException: "
					+ e.getMessage());
		} catch (ObjectDoesNotExistException e) {
			System.out.println("CLP call - ObjectDoesNotExistException: "
					+ e.getMessage());
		} finally {
			try {
				// Done with the system.
				system.disconnectAllServices();
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
}
