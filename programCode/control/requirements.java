public String DynamicImage(String categoryid,int cut,int width,int height){

		StringBuffer imgStr = new StringBuffer();
		StringBuffer thePics1 = new StringBuffer();
		StringBuffer theLinks1 = new StringBuffer();
		StringBuffer theTexts1 = new StringBuffer();
	
		imgStr.append("<div id=picViwer1  style='background-color: #ffffff' align=center></div><SCRIPT src='js/dynamicImage.js' type=text/javascript></SCRIPT>\n<script language=JavaScript>\n");
		thePics1.append("var thePics1=\n'");
		theLinks1.append("var theLinks1='");
		theTexts1.append("var theTexts1='");
		
		List<HashMap> co = this.select("select * from xinwentongzhi where shouyetupian<>'' and shouyetupian<>'null'  and shouyetupian  like '%.jpg' order by id desc",1,6);
		int i = co.size();
		
		int j = 0; 
		for(HashMap b:co)
		{
			j++; 
		int id = Integer.parseInt(b.get("id").toString()) ;
		String title = Info.subStr(b.get("biaoti").toString(), 21) ;
		
		String url = ""+b.get("shouyetupian");
		
		String purl = "gg_detail.jsp?id="+b.get("id");
		
		if(j!=i){
		thePics1.append(url.replaceAll("\n", "")+"|");
		theLinks1.append(purl+"|");
		theTexts1.append(title+"|");
		}
		if(j==i)
		{
			thePics1.append(url.replaceAll("\n", ""));
			theLinks1.append("gg_detail.jsp?id="+b.get("id"));
			theTexts1.append(title);
		}
		
		}
	   thePics1.append("';");
		
		theLinks1.append("';");
		theTexts1.append("';");
		imgStr.append(thePics1+"\n");
		imgStr.append(theLinks1+"\n");
		imgStr.append(theTexts1+"\n");
		imgStr.append("\n setPic(thePics1,theLinks1,theTexts1,"+width+","+height+",'picViwer1');</script>");
		return imgStr.toString();
	}
public HashMap getmap(String id,String table)
		{
			List<HashMap> list = new ArrayList();
			try {
				Statement st = conn.createStatement();
				//System.out.println("select * from "+table+" where id="+id);
			    ResultSet rs = st.executeQuery("select * from "+table+" where id="+id);
			    ResultSetMetaData rsmd = rs.getMetaData();
	            while(rs.next())
			    {
			    	HashMap map = new HashMap();
			    	int i = rsmd.getColumnCount();
			    	for(int j=1;j<=i;j++)
			    	{
			    		if(!rsmd.getColumnName(j).equals("ID"))
			    		{
			    			String str = rs.getString(j)==null?"": rs.getString(j);
			    			if(str.equals("null"))str = "";
			    			map.put(rsmd.getColumnName(j), str);
			    		}
			    		else
			    			map.put("id", rs.getString(j));
			    	}
			    	list.add(map);
			    }
			    rs.close();
			    st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list.get(0);
		}