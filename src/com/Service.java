package com;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
import model.Patients;
import javax.ws.rs.Path;

public class Service {


//@Path("/Patients") 

	Patients Obj = new Patients();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)			
	public String readItems()
	 {
	 return Obj.readItems();
	 }

		@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("patientID") String itemCode,
	 @FormParam("name") String itemName,
	 @FormParam("email") String itemPrice,
	 @FormParam("password") String itemDesc)
	{
	 String output = Obj.insertItem(itemCode, itemName, itemPrice, itemDesc);
	return output;
	}
		
		
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateItem(String PaymentData){

			JsonObject payments = new JsonParser().parse(PaymentData).getAsJsonObject();

			

		String PaymentID = payments.get("patientID").getAsString();
		String itemCode = payments.get("name").getAsString();
		String itemName = payments.get("email").getAsString();
		String itemDesc = payments.get("password").getAsString();
		

		String output = Obj.updateItem(PaymentID, itemCode, itemName, itemDesc);
		return output;


		}
		
		
		
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deletePayment(String paymentDatString)
		{
		//Convert the input string to an XML document

			org.jsoup.nodes.Document doc = Jsoup.parse(paymentDatString, " ", org.jsoup.parser.Parser.xmlParser());

			String PaymentID = doc.select("patientID").text();		//xml input

			String output = Obj.deleteItem(PaymentID);
			return output;

		}

}
		

