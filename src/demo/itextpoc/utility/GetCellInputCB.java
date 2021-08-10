/**
 * 
 */
package demo.itextpoc.utility;

/**
 * @author sandeepjakkaraju
 *
 */
public class GetCellInputCB {

	private int count = 0;
	private String cb;

	/**
	 * 
	 */
	public GetCellInputCB(String cb) {
		this.cb = cb;
	}

	public String getCellInputName() {

		return cb + (++count);
	}

}
