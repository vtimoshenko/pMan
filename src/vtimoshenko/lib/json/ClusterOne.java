package vtimoshenko.lib.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.LinkedList;

/**
 * Хранилище строковых данных в виде дерева.
 * Реализует формат JSON.
 * Требует библиотеки SimpleJSON.
 * Ветвь - объект или массив
 * Лист - строка
 * 
 * Адрес указывается в виде: node1.node2.node3.data
 * Адрес корня - пустой
 * 
 * @author vtimoshenko
 */
public class ClusterOne
{
	//			//				//
	private		JSONObject		root;

	
	
	//базовые методы****************************************************
	/**
	 * Положить объект в дерево
	 * 
	 * @param trace	путь к ветви 
	 * @param key	имя новой ветви
	 * @param data	объект ветви
	 * @return		статус выполнения
	 * @throws ClusterException asd
	 */
	public		boolean			put (String trace, String key, JSONObject data)
	throws ClusterException	
	{
		Object current = trace(trace);
		if (current==null) return false;
		if (current.getClass().getSimpleName().equals("JSONObject"))
		{
			JSONObject c = (JSONObject) current;
			c.put(key, data);
			return true;
		}
		else if (current.getClass().getSimpleName().equals("JSONArray"))
		{
			JSONArray c = (JSONArray) current;
			c.add(data);
			return true;
		}
		else return false;
	}
	/**
	 * Положить массив в дерево
	 * 
	 * @param trace	путь к ветви
	 * @param key	имя новой ветви
	 * @param data	массив ветви
	 * @return		статус выполнения
	 * @throws ClusterException
	 */
	public		boolean			put (String trace, String key, JSONArray data)
	throws ClusterException
	{
		Object current = trace(trace);
		if (current==null) return false;
		if (current.getClass().getSimpleName().equals("JSONObject"))
		{
			JSONObject c = (JSONObject) current;
			c.put(key, data);
			return true;
		}
		else if (current.getClass().getSimpleName().equals("JSONArray"))
		{
			JSONArray c = (JSONArray) current;
			c.add(data);
			return true;
		}
		else return false;
	}
	/**
	 * Положить строку в дерево
	 * 
	 * @param trace	путь к ветви
	 * @param key	имя листа
	 * @param data	текст листа
	 * @return		статус выполнения
	 * @throws ClusterException
	 */
	public		boolean			put (String trace, String key, String data)
	throws ClusterException
	{
		if (data==null) data = "";
		Object current = trace(trace);
		if (current==null) return false;
		if (current.getClass().getSimpleName().equals("JSONObject"))
		{
			JSONObject c = (JSONObject) current;
			c.put(key, data);
			return true;
		}
		else if (current.getClass().getSimpleName().equals("JSONArray"))
		{
			JSONArray c = (JSONArray) current;
			c.add(data);
			return true;
		}
		else return false;
	}
	/**
	 * Положить следующий объект в массив
	 * Атогенерация индекса
	 * 
	 * @param trace	путь к ветви
	 * @param data	данные объекта
	 * @return		статус выполнения
	 * @throws ClusterException
	 */
	public		boolean			put (String trace, JSONObject data)
	throws ClusterException
	{
		Object current = trace(trace);
		if (current==null) return false;
		if (current.getClass().getSimpleName().equals("JSONObject"))
		{
			JSONObject c = (JSONObject) current;
			c.put("" + c.size(), data);
			return true;
		}
		else if (current.getClass().getSimpleName().equals("JSONArray"))
		{
			JSONArray c = (JSONArray) current;
			c.add(data);
			return true;
		}
		else return false;
	}
	/**
	 * Положить следующий массив в массив
	 * Автогенерация индекса 
	 * 
	 * @param trace	путь к ветви
	 * @param data	данные объекта
	 * @return		статус выполнения
	 * @throws ClusterException
	 */
	public		boolean			put (String trace, JSONArray data)
	throws ClusterException
	{
		Object current = trace(trace);
		if (current==null) return false;
		if (current.getClass().getSimpleName().equals("JSONObject"))
		{
			JSONObject c = (JSONObject) current;
			c.put("" + c.size(), data);
			return true;
		}
		else if (current.getClass().getSimpleName().equals("JSONArray"))
		{
			JSONArray c = (JSONArray) current;
			c.add(data);
			return true;
		}
		else return false;
	}
	/**
	 * Положить следующую строчку в массив
	 * Автогенерация индекса
	 * 
	 * @param trace
	 * @param data
	 * @return
	 * @throws ClusterException
	 */
	public		boolean			put (String trace, String data)
	throws ClusterException
	{
		if (data==null) data = "";
		Object current = trace(trace);
		if (current==null) return false;
		if (current.getClass().getSimpleName().equals("JSONObject"))
		{
			JSONObject c = (JSONObject) current;
			c.put("" + c.size(), data);
			return true;
		}
		else if (current.getClass().getSimpleName().equals("JSONArray"))
		{
			JSONArray c = (JSONArray) current;
			c.add(data);
			return true;
		}
		else return false;
	}
	//******************************************************************
	
	
	
	//удаленное создание объектов***************************************
	/**
	 * Удалённое создание массива
	 * 
	 * @param trace	путь к ветви
	 * @param key	имя массива
	 * @return		статус
	 * @throws ClusterException
	 */
	public		boolean 		createArray(String trace, String key)
	throws ClusterException
	{
		JSONArray nobj = new JSONArray();
		Object current = trace(trace);
		if (current==null) return false;
		if (current.getClass().getSimpleName().equals("JSONObject"))
		{
			JSONObject c = (JSONObject) current;
			c.put(key, nobj);
			return true;
		}
		else if (current.getClass().getSimpleName().equals("JSONArray"))
		{
			JSONArray c = (JSONArray) current;
			c.add(nobj);
			return true;
		}
		else return false;
	}
	/**
	 * Удалённое создание объекта
	 * 
	 * @param trace	путь к объекту
	 * @param key	имя объекта
	 * @return		статус
	 * @throws ClusterException
	 */
	public		boolean 		createObject(String trace, String key)
	throws ClusterException
	{
		JSONObject nobj = new JSONObject();
		Object current = trace(trace);
		if (current==null) return false;
		if (current.getClass().getSimpleName().equals("JSONObject"))
		{
			JSONObject c = (JSONObject) current;
			c.put(key, nobj);
			return true;
		}
		else if (current.getClass().getSimpleName().equals("JSONArray"))
		{
			JSONArray c = (JSONArray) current;
			c.add(nobj);
			return true;
		}
		else return false;
	}
	/**
	 * Удалённое создание следующего массива
	 * Автогенерация индекса
	 * 
	 * @param trace
	 * @return
	 * @throws ClusterException
	 */
	public		boolean 		createArray(String trace)
	throws ClusterException
	{
		JSONArray nobj = new JSONArray();
		Object current = trace(trace);
		if (current==null) return false;
		if (current.getClass().getSimpleName().equals("JSONObject"))
		{
			JSONObject c = (JSONObject) current;
			c.put("" + c.size(), nobj);
			return true;
		}
		else if (current.getClass().getSimpleName().equals("JSONArray"))
		{
			JSONArray c = (JSONArray) current;
			c.add(nobj);
			return true;
		}
		else return false;
	}
	/**
	 * удаленное создание следующего объекта
	 * Автогенерация индекса
	 * 
	 * @param trace
	 * @return
	 * @throws ClusterException
	 */
	public		boolean 		createObject(String trace)
	throws ClusterException
	{
		JSONObject nobj = new JSONObject();
		Object current = trace(trace);
		if (current==null) return false;
		if (current.getClass().getSimpleName().equals("JSONObject"))
		{
			JSONObject c = (JSONObject) current;
			c.put("" + c.size(), nobj);
			return true;
		}
		else if (current.getClass().getSimpleName().equals("JSONArray"))
		{
			JSONArray c = (JSONArray) current;
			c.add(nobj);
			return true;
		}
		else return false;
	}
	//******************************************************************
	
	
	
	//объектная манипуляция*********************************************
	/**
	 * получить ветку дерева
	 * 
	 * @param trace	путь к ветви
	 * @return		Объект ClusterOne
	 * @throws ClusterException
	 */
	public		ClusterOne		getPart (String trace)
	throws ClusterException
	{
		ClusterOne ret = new ClusterOne();
		String part = get(trace);
		
		if (part==null) return ret;
		
		ret.getFromString(part);
		return ret;
	}
	/**
	 * нарастить ветку
	 * 
	 * @param trace	путь к ветви
	 * @param key	имя новой ветви
	 * @param part	данные ветви
	 * @return		статус
	 * @throws ClusterException
	 */
	public		boolean			putPart (String trace, String key, ClusterOne part)
	throws ClusterException
	{
		Object current = trace(trace);
		if (current==null) return false;
		if (current.getClass().getSimpleName().equals("JSONObject"))
		{
			JSONObject c = (JSONObject) current;
			c.put(key, part.trace(""));
			return true;
		}
		else if (current.getClass().getSimpleName().equals("JSONArray"))
		{
			JSONArray c = (JSONArray) current;
			c.add(part.trace(""));
			return true;
		}
		else return false;
	}
	//******************************************************************
	
	
	
	//Другие манипуляции с данными**************************************
	/**
	 * Поиск индекса по значению
	 * 
	 * @param trace		Путь к ветви
	 * @param option	Имя листа
	 * @param value		Значение искомого листа
	 * @return
	 * @throws ClusterException
	 */
	public		String			search (String trace, String option, String value)
	throws ClusterException
	{
		Object current = trace(trace);
		if (current==null) 
		{	
			return null;
		}
		if (current.getClass().getSimpleName().equals("JSONObject"))
		{
			String index = null;
			JSONObject c = (JSONObject) current;
			
			Object[] objs = c.keySet().toArray();
			
			for (int i=0;i<objs.length;i++)
			{
				if (get(trace + "." + objs[i].toString() + "." + option).equals(value))
					index = objs[i].toString();
			}
			return index;
		}
		else if (current.getClass().getSimpleName().equals("JSONArray"))
		{
			String index = null;
			JSONArray c = (JSONArray)current;
			for (int i=0;i<c.size();i++)
			{
				if (get(trace + "." + i + "." + option).equals(value))
					index = "" + i;
			}
			return index;
		}
		else
		{
			return null;
		}
	}
	/**
	 * Создание индексированной копии
	 * 
	 * @param sourceTrace		адрес исходника
	 * @param sourceIndex		поле индексации (по которому создается индекс)
	 * @param destinationTrace	адрес результата
	 * @param name				имя ветви результата
	 * @return					статус
	 * @throws ClusterException
	 */
	public		boolean			createIndex (String sourceTrace, String sourceIndex, String destinationTrace, String name)
	throws ClusterException
	{
		Object source = trace(sourceTrace);//узнать что за массив
		if (source==null) { return false; }
		
		JSONObject nobj = new JSONObject();//новый массив
		
		if (source.getClass().getSimpleName().equals("JSONObject"))
		{//Если объект
			JSONObject c = (JSONObject) source;//текущий объект
			
			Object[] objs = c.keySet().toArray();//ключи текущего объекта
			for (int i=0;i<objs.length;i++)//перебор ключей текущего объекта
			{
				Object okey = trace(sourceTrace + "." + objs[i].toString() + "." + sourceIndex);
				if (!okey.getClass().getSimpleName().equals("String")) return false;
				String key = okey.toString();
				//узнали индексный ключ
				
				//теперь создать копию по адресу
				Object current = trace(sourceTrace + "." + objs[i].toString());
				nobj.put(key, current);
			}
		}
		else if (source.getClass().getSimpleName().equals("JSONArray"))
		{//если массив
			JSONArray c = (JSONArray) source;//текущий массив
			
			for (int i=0;i<c.size();i++)
			{
				Object okey = trace(sourceTrace + "." + i + "." + sourceIndex);
				if (!okey.getClass().getSimpleName().equals("String")) return false;
				String key = okey.toString();
				//узнали индексный ключ
				
				//теперь создать копию по адресу
				Object current = trace(sourceTrace + "." + i);
				nobj.put(key, current);
			}
		}
		else return false;
		
		put(destinationTrace, name, nobj);
		return true;
	}
	/**
	 * Очистить ветвь или лист
	 * 
	 * @param trace	путь к листу или ветви
	 * @throws ClusterException
	 */
	public		void 			empty (String trace)
	throws ClusterException
	{
		Object current = trace(trace);
		if (current==null) return;
		if (current.getClass().getSimpleName().equals("JSONObject"))
		{
			JSONObject c = (JSONObject) current;
			c.clear();
		}
		else if (current.getClass().getSimpleName().equals("JSONArray"))
		{
			JSONArray c = (JSONArray) current;
			c.clear();
		}
		else if (current.getClass().getSimpleName().equals("String"))
		{
			current = "";
		}
	}
	/**
	 * Удалить элемент по адресу
	 * 
	 * @param trace	путь к ветви
	 * @param key	имя листа или ветви
	 * @throws ClusterException
	 */
	public 		void 			delete (String trace, String key)
	throws ClusterException
	{
		Object current = trace(trace);
		if (current==null) return;
		if (current.getClass().getSimpleName().equals("JSONObject"))
		{
			JSONObject c = (JSONObject) current;
			c.remove(key);
		}
		else if (current.getClass().getSimpleName().equals("JSONArray"))
		{
			JSONArray c = (JSONArray) current;
			c.remove(Integer.parseInt(key));
		}
		else if (current.getClass().getSimpleName().equals("String"))
		{
			current = "";
		}
	}
	/**
	 * Удалить элемент по адресу
	 * 
	 * @param trace		путь к ветви
	 * @throws ClusterException
	 */
	public 		void 			delete (String trace)
	throws ClusterException
	{
		int dotindex = trace.lastIndexOf(".");
		
		String key = trace.substring(dotindex+1, trace.length());
		trace = trace.substring(dotindex, trace.length());
		Object current = trace(trace);
		if (current==null) return;
		if (current.getClass().getSimpleName().equals("JSONObject"))
		{
			JSONObject c = (JSONObject) current;
			c.remove(key);
		}
		else if (current.getClass().getSimpleName().equals("JSONArray"))
		{
			JSONArray c = (JSONArray) current;
			c.remove(Integer.parseInt(key));
		}
		else if (current.getClass().getSimpleName().equals("String"))
		{
			current = "";
		}
	}
	/**
	 * узнать размер ветви (количество потомков)
	 * 
	 * @param trace	путь к ветви
	 * @return		количество элементов в ветви
	 * @throws ClusterException
	 */
	public		int				size (String trace)
	throws ClusterException
	{
		Object current = trace(trace);
		if (current==null) return 0;
		if (current.getClass().getSimpleName().equals("JSONObject"))
		{
			JSONObject c = (JSONObject) current;
			return c.size();
		}
		else if (current.getClass().getSimpleName().equals("JSONArray"))
		{
			JSONArray c = (JSONArray) current;
			return c.size();
		}
		else if (current.getClass().getSimpleName().equals("String"))
		{
			String c = (String) current;
			return c.length();
		}
		else return 0;
	}
	/**
	 * получить значение по адресу
	 * 
	 * @param trace	путь к листу или ветви
	 * @return		текст листа или JSON представление ветви
	 * @throws ClusterException
	 */
	public		String			get (String trace)
	throws ClusterException
	{
		Object current = trace(trace);
		if (current.getClass().getSimpleName().equals("JSONObject"))
		{
			JSONObject c = (JSONObject) current;
			return c.toJSONString();
		}
		else if (current.getClass().getSimpleName().equals("JSONArray"))
		{
			JSONArray c = (JSONArray) current;
			return c.toJSONString();
		}
		else if (current.getClass().getSimpleName().equals("String"))
		{
			String c = (String) current;
			return c;
		}
		else return null;
	}
	//******************************************************************
	
	
	
	//******************************************************************
	/**
	 * получить строку JSON описывающую весь объект 
	 * 
	 * @return	строка JSON
	 */
	public		String 			getJSON()
	{
		return root.toJSONString();
	}
	/**
	 * получить сам JSON объект корня
	 * 
	 * @return
	 */
	public		JSONObject 		getJSONObject()
	{
		return root;
	}
	/**
	 * считать с файла (данные в формате JSON с однострочными комментариями //)
	 * 
	 * @param fname	имя файла
	 * @return		статус
	 * @throws ClusterException
	 */
	public		boolean 		getFromFile(String fname)
	throws ClusterException
	{
		try
		{
			InputStreamReader isr = new InputStreamReader(new FileInputStream(fname),"CP1251");
			StringBuffer allData = new StringBuffer("");
			int c;
			while((c = isr.read())!=-1)
			{
				allData.appendCodePoint(c);
			}
			isr.close();
			String line = allData.toString();
			//System.out.println("before:" + line);
			line = line.replaceAll("//.*\r\n", "\r\n");
			//System.out.println("after:" + line);
			//System.out.println("READING: " + allData);
			return getFromString(line);
		}
		catch (Exception e)
		{
			//e.printStackTrace();
			throw new ClusterException("Extracting data from File " + fname + " FAILURE with message " + e.getMessage());
		}
	}
	
	/**
	 * распарсить из строки
	 * 
	 * @param target	сформировать содержимое из строки JSON
	 * @return			статус
	 * @throws ClusterException
	 */
	public		boolean 		getFromString(String target)
	throws ClusterException
	{
		try
		{
			JSONParser pars = new JSONParser();
			root = (JSONObject) pars.parse(target);
			return true;
		}
		catch (Exception e)
		{
			//e.printStackTrace();
			throw new ClusterException ("Extracting data from String FAILURE with message " + e.getMessage());
		}
	}
	/**
	 * сгенерировать из набора SQL
	 * 
	 * @param rs	Набор данных SQL
	 * @return		статус
	 * @throws ClusterException
	 */
	public		boolean 		getFromRS(ResultSet rs)
	throws ClusterException
	{
		LinkedList<String> Colnames = new LinkedList<String>();			//Набор имён столбцов
		try
		{
			ResultSetMetaData rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			for (int i=1;i<=cols;i++)//считать имена колонок
			{
				Colnames.add(rsmd.getColumnLabel(i));
			}
			int cind=0;
			while (rs.next())
			{
				JSONObject cob = new JSONObject();
				for (int i=1;i<=cols;i++)
				{
					if (rs.getString(i) == null) cob.put(Colnames.get(i-1), "NULL");//Если встречаем null записываем строковый эквивалент дабы избежать вылетов
					else cob.put(Colnames.get(i-1), rs.getString(i).trim());
				}
				root.put("" + cind, cob);
				cind++;
			}
		}
		catch (Exception e)
		{
			throw new ClusterException ("Extracting data from Result Set FAILURE with message " + e.getMessage());
		}
		return true;
	}
	/**
	 * Записать в файл (данные в формате JSON)
	 * 
	 * @param fname	имя файла
	 * @return		Статус
	 * @throws ClusterException
	 */
	public		boolean 		setToFile(String fname)
	throws ClusterException
	{
		try
		{
			OutputStreamWriter ow = new OutputStreamWriter(new FileOutputStream(fname), "CP1251");
			ow.write(root.toJSONString());
			ow.flush();
			ow.close();
	        return true;
	    }
	    catch (Exception e)
	    {
	    	throw new ClusterException ("Exporting data to file " + fname + " FAILURE with message " + e.getMessage());
	    }
	}
	//******************************************************************
	
	
	
	//служебные методы**************************************************
	/**
	 * трассировка дерева до искомого элемента
	 * 
	 * @param trace путь
	 * @return		Объект листа или ветви
	 * @throws ClusterException
	 */
	public		Object 			trace(String trace)
	throws ClusterException
	{
		try
		{
			if (trace.equals("")) return root;
			String arr[] = trace.split("[.]");
			Object current = root.get(arr[0]);
			for (int i=1;i<arr.length;i++)
			{
				if (current.getClass().getSimpleName().equals("JSONObject"))
				{
					JSONObject c = (JSONObject) current;
					current = c.get(arr[i]);
				}
				else if (current.getClass().getSimpleName().equals("JSONArray"))
				{
					JSONArray c = (JSONArray) current;
					if (Integer.parseInt(arr[i]) >= c.size()) return null;
					current = c.get(Integer.parseInt(arr[i]));
				}
				else return null;
			}
			return current;
		}
		catch (Exception e)
		{
			throw new ClusterException("Unreachable path (" + trace + ")");
		}
	}
	/**
	 * Определение существования ветви/листа
	 * 
	 * @param trace путь
	 * @return
	 */
	public		boolean 		exists(String trace)
	{
		try
		{
			if (trace.equals("")) return true;
			
			String arr[] = trace.split("[.]");
			Object current = root.get(arr[0]);
			for (int i=1;i<arr.length;i++)
			{
				if (current.getClass().getSimpleName().equals("JSONObject"))
				{
					JSONObject c = (JSONObject) current;
					current = c.get(arr[i]);
				}
				else if (current.getClass().getSimpleName().equals("JSONArray"))
				{
					JSONArray c = (JSONArray) current;
					if (Integer.parseInt(arr[i]) >= c.size()) return false;
					current = c.get(Integer.parseInt(arr[i]));
				}
				else return false;
			}
			if (current.getClass().getSimpleName().equals("JSONObject"))
			{
				return true;
			}
			else if (current.getClass().getSimpleName().equals("JSONArray"))
			{
				return true;
			}
			else if (current.getClass().getSimpleName().equals("String"))
			{
				return true;
			}
			else return false;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	//******************************************************************
	
	
	
	/**
	 * Конструктор пустого дерева
	 */
	public		ClusterOne ()
	{
		root = new JSONObject();
	}
	/**
	 * Конструктор из файла
	 * 
	 * @param fname имя файла
	 * @throws ClusterException
	 */
	public		ClusterOne (String fname)
	throws ClusterException
	{
		root = new JSONObject();
		this.getFromFile(fname);
	}
	/**
	 * Конструктор с данными
	 * 
	 * @param nobj	Объект данных для начальной инициализации
	 */
	public		ClusterOne(JSONObject nobj)
	{
		root = nobj;
	}
}
