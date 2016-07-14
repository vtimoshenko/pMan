package vtimoshenko.lib.json;




/**
 * Исключение при работе с JSON Cluster
 * 
 * @author vtimoshenko
 *
 */
public class ClusterException extends Exception 
{
	private		String		Message;
	private		String		Dump;
	
	/**
	 * Конструктор по умолчанию пустой
	 */
	public 		ClusterException ()
	{
		Message = "";
		Dump = "";
	}
	/**
	 * Конструктор с сообщением
	 * 
	 * @param nMessage сообщение об ошибке
	 */
	public 		ClusterException (String nMessage)
	{
		Message = nMessage;
		Dump = "";
	}
	/**
	 * Конструктор с сообщением и дампом
	 * 
	 * @param nMessage сообщение
	 * @param Subject субъект, сгенерировавший исключение
	 */
	public 		ClusterException (String nMessage, ClusterOne Subject)
	{
		Message = nMessage;
		Dump = Subject.getJSON();
	}
	/**
	 * @return сообщение об ошибке
	 */
	public 		String 		getMessage ()
	{
		return Message;
	}
	/**
	 * @return дамп памяти
	 */
	public		String		getDump()
	{
		return Dump;
	}
	/**
	 * Вывод детального сообщения об ошибке
	 */
	public 		void 		printErrorReport()
	{
		System.out.println("ClusterException with message: " + Message + "\nData Dump:\n" + Dump+ "\n\n");
		this.printStackTrace();
	}
}
