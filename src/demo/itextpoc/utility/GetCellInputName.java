/**
 * 
 */
package demo.itextpoc.utility;

/**
 * @author sandeepjakkaraju
 *
 */
public class GetCellInputName {

	private int count = 0;

	/**
	 * 
	 */
	public GetCellInputName() {
		// TODO Auto-generated constructor stub
	}

	public String getCellInputName() {

		return "cellInput" + (++count);
	}

}
