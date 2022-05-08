
package primitives;

/**
 * Material class (PDS) with builder design pattern
 *
 * @author Yaniv and Yoav
 */
public class Material {

    public Double3 kD = new Double3(0);
    public Double3 kS = new Double3(0);
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
     * @param kS-is Double3
     * @return The object material (this)
     */
    public Material setKs(Double3 kS) {
        this.kS = kS;
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
     * @return The kD
     */
    public Double3 getkD() {
        return kD;
    }

    /**
     * @return The kS
     */
    public Double3 getkS() {
        return kS;
    }

    /**
     * @return The nShininess
     */
    public int getShininess() {
        return nShininess;
    }


}
