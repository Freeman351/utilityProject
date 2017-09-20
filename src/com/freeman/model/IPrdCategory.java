package com.freeman.model;

import java.math.BigDecimal;

public interface IPrdCategory {

	public abstract String getTtblid();

	public abstract void setTtblid(String ttblid);

	public abstract String getTtblcd();

	public abstract void setTtblcd(String ttblcd);

	public abstract String getTabr();

	public abstract void setTabr(String tabr);

	public abstract String getTcmnf1();

	public abstract void setTcmnf1(String tcmnf1);

	public abstract String getTcmnf2();

	public abstract void setTcmnf2(String tcmnf2);

	public abstract BigDecimal getTnumf1();

	public abstract void setTnumf1(BigDecimal tnumf1);

	public abstract BigDecimal getTnumf2();

	public abstract void setTnumf2(BigDecimal tnumf2);

	public abstract BigDecimal getTnumf3();

	public abstract void setTnumf3(BigDecimal tnumf3);

	public abstract BigDecimal getTnumf4();

	public abstract void setTnumf4(BigDecimal tnumf4);

	public abstract String getTind1();

	public abstract void setTind1(String tind1);

	public abstract String getTind2();

	public abstract void setTind2(String tind2);

	public abstract String getTind3();

	public abstract void setTind3(String tind3);

	public abstract String getTind4();

	public abstract void setTind4(String tind4);

	public abstract String getTdepcd();

	public abstract void setTdepcd(String tdepcd);

	public abstract String getTdepc1();

	public abstract void setTdepc1(String tdepc1);

	public abstract int getTaccd1();

	public abstract void setTaccd1(int taccd1);

	public abstract int getTaccd2();

	public abstract void setTaccd2(int taccd2);

	public abstract int getTcrtda();

	public abstract void setTcrtda(int tcrtda);

	public abstract int getTmodrd();

	public abstract void setTmodrd(int tmodrd);

	public abstract String getTusrid();

	public abstract void setTusrid(String tusrid);

	public abstract char getTdivcd();

	public abstract void setTdivcd(char tdivcd);

	public abstract boolean equals(Object other);

	public abstract int hashCode();

}