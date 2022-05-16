
package primitives;

/**
 * Material class (PDS) with builder design pattern
 *
 * @author Yaniv and Yoav
 */
public class Material {

    public Double3 kD =  Double3.ZERO;
    public Double3 kS = Double3.ZERO;
    public Double3 kT= Double3.ZERO;
    public Double3 kR= Double3.ZERO;
    public int nShininess = 0;

    /**
     * setter for kD
     *
     * @param kD- kD is Double3
     * @return The object material (this)
     */
    public Material setKd(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * setter for kD
     *
     * @param kD- is double
     * @return The object material (this)
     */
    public Material setKd(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * setter for kS
     *
     * @param kS-is double
     * @return The object material (this)
     */
    public Material setKs(double kS) {
        this.kS = new Double3(kS);
        return this;
    }
    /**
     * setter for kS
     *
     * @param kS-is Double3
     * @return The object material (this)
     */
    public Material setKs(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * setter for kT
     *
     * @param kT-is double
     * @return The object material (this)
     */
    public Material setKt(double kT) {
        this.kT = new Double3(kT);
        return this;
    }
    /**
     * setter for kt
     *
     * @param kT-is Double3
     * @return The object material (this)
     */
    public Material setKt(Double3 kT) {
        this.kT = kT;
        return this;
    }

    /**
     * setter for kr
     *
     * @param kR-is double
     * @return The object material (this)
     */
    public Material setKr(double kR) {
        this.kR = new Double3(kR);
        return this;
    }
    /**
     * setter for kr
     *
     * @param kR-is Double3
     * @return The object material (this)
     */
    public Material setKr(Double3 kR) {
        this.kR = kR;
        return this;
    }



    /**
     * setter for nShininess
     *
     * @param nShininess
     * @return The object material (this)
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    /**
     * @return The nShininess
     */
    public int getShininess() {
        return nShininess;
    }


}
