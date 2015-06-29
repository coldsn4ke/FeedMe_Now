package com.csoft.wong.feedmenow;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

import java.io.StringReader;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XMLHandler {

    private static final String TAG = "XMLHandler";

    //public JSONHandler(){};

    public HashMap<String, HashMap<String, String>> parseXml(String xmlstring) {

        //xmlDocument.createElement("myxml");
        try {
            //Document xmlDocument = loadXMLFromString(xmlstring);

            DocumentBuilderFactory dbf =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader("<Recipe xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns:xsd='http://www.w3.org/2001/XMLSchema'>  <RecipeID>1185708</RecipeID>  <Title>Smoky 3 Cheese Fondue with Toasted Garlic Buttered Croissants. (Note from BigOven)</Title>  <Description />  <Cuisine />  <Category>Appetizers</Category>  <Subcategory>Cheese</Subcategory>  <PrimaryIngredient />  <StarRating>0</StarRating>  <WebURL>http://www.bigoven.com/recipe/smoky-3-cheese-fondue-with-toasted-garlic-buttered-croissants/1185708</WebURL>  <ImageURL>http://redirect.bigoven.com/pics/rs/640/smoky3cheesefonduewithtoastedg-1b2abb.jpg</ImageURL>  <ReviewCount>0</ReviewCount>  <MedalCount>0</MedalCount>  <FavoriteCount>2</FavoriteCount>  <Poster>    <UserID>2782649</UserID>    <UserName>halfbakedharvestrecipes</UserName>    <ImageURL48>http://images.bigoven.com/image/upload/t_recipe-48,d_avatar-default.png/v1426628205/halfbakedharvestrecipes-av1.jpg</ImageURL48>    <IsPremium>false</IsPremium>    <IsKitchenHelper>false</IsKitchenHelper>    <PremiumExpiryDate xsi:nil='true' />    <MemberSince>2015-03-13T23:36:00Z</MemberSince>    <IsUsingRecurly>false</IsUsingRecurly>  </Poster>  <Ingredients />  <Instructions>This is a free trial developer key at api.bigoven.com. Production usage of the BigOven API requires a paid plan, which helps offset BigOven's considerable hosting costs. This message will not appear on any paid plan; when you're ready, please visit your developer console at http://api.bigoven.com to license an upgraded key. Thank you!</Instructions>  <YieldNumber>6</YieldNumber>  <YieldUnit>Servings</YieldUnit>  <TotalMinutes xsi:nil='true' />  <ActiveMinutes xsi:nil='true' />  <NutritionInfo>    <SingularYieldUnit>Paid API plan required for nutrition.</SingularYieldUnit>    <TotalCalories>0</TotalCalories>    <TotalFat>0</TotalFat>    <CaloriesFromFat>0</CaloriesFromFat>    <TotalFatPct>0</TotalFatPct>    <SatFat>0</SatFat>    <SatFatPct>0</SatFatPct>    <MonoFat>0</MonoFat>    <PolyFat>0</PolyFat>    <TransFat>0</TransFat>    <Cholesterol>0</Cholesterol>    <CholesterolPct>0</CholesterolPct>    <Sodium>0</Sodium>    <SodiumPct>0</SodiumPct>    <Potassium>0</Potassium>    <PotassiumPct>0</PotassiumPct>    <TotalCarbs>0</TotalCarbs>    <TotalCarbsPct>0</TotalCarbsPct>    <DietaryFiber>0</DietaryFiber>    <DietaryFiberPct>0</DietaryFiberPct>    <Sugar>0</Sugar>    <Protein>0</Protein>    <ProteinPct>0</ProteinPct>  </NutritionInfo>  <IsPrivate>false</IsPrivate>  <CreationDate>2015-04-27T15:56:42.733Z</CreationDate>  <LastModified>2015-04-27T10:57:22.247Z</LastModified>  <IsBookmark>true</IsBookmark>  <BookmarkURL>http://www.halfbakedharvest.com/smoky-3-cheese-fondue-toasted-garlic-buttered-croissants/</BookmarkURL>  <BookmarkSiteLogo />  <BookmarkImageURL>http://www.halfbakedharvest.com/wp-content/uploads/2015/02/Smoky-3-Cheese-Fondue-with-Toasted-Garlic-Buttered-Croissants-1.jpg</BookmarkImageURL>  <IsRecipeScan xsi:nil='true' />  <MenuCount>0</MenuCount>  <NotesCount>0</NotesCount>  <AllCategoriesText />  <IsSponsored>false</IsSponsored>  <VariantOfRecipeID xsi:nil='true' />  <Collection />  <AdminBoost xsi:nil='true' />  <VerifiedDateTime xsi:nil='true' />  <MaxImageSquare>512</MaxImageSquare>  <ImageSquares>    <int>512</int>    <int>480</int>    <int>320</int>    <int>256</int>    <int>200</int>    <int>128</int>    <int>120</int>    <int>64</int>    <int>48</int>    <int>36</int>  </ImageSquares>  <HeroPhotoUrl>http://images.bigoven.com/image/upload/v1430150210/smoky3cheesefonduewithtoastedg-1b2abb.jpg</HeroPhotoUrl></Recipe>"));

            Document doc = db.parse(is);
            NodeList nodes = doc.getElementsByTagName("employee");


            Document xmlDocument = loadXMLFromString(xmlstring); //"<Recipe xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns:xsd='http://www.w3.org/2001/XMLSchema'>  <RecipeID>1185708</RecipeID>  <Title>Smoky 3 Cheese Fondue with Toasted Garlic Buttered Croissants. (Note from BigOven)</Title>  <Description />  <Cuisine />  <Category>Appetizers</Category>  <Subcategory>Cheese</Subcategory>  <PrimaryIngredient />  <StarRating>0</StarRating>  <WebURL>http://www.bigoven.com/recipe/smoky-3-cheese-fondue-with-toasted-garlic-buttered-croissants/1185708</WebURL>  <ImageURL>http://redirect.bigoven.com/pics/rs/640/smoky3cheesefonduewithtoastedg-1b2abb.jpg</ImageURL>  <ReviewCount>0</ReviewCount>  <MedalCount>0</MedalCount>  <FavoriteCount>2</FavoriteCount>  <Poster>    <UserID>2782649</UserID>    <UserName>halfbakedharvestrecipes</UserName>    <ImageURL48>http://images.bigoven.com/image/upload/t_recipe-48,d_avatar-default.png/v1426628205/halfbakedharvestrecipes-av1.jpg</ImageURL48>    <IsPremium>false</IsPremium>    <IsKitchenHelper>false</IsKitchenHelper>    <PremiumExpiryDate xsi:nil='true' />    <MemberSince>2015-03-13T23:36:00Z</MemberSince>    <IsUsingRecurly>false</IsUsingRecurly>  </Poster>  <Ingredients />  <Instructions>This is a free trial developer key at api.bigoven.com. Production usage of the BigOven API requires a paid plan, which helps offset BigOven's considerable hosting costs. This message will not appear on any paid plan; when you're ready, please visit your developer console at http://api.bigoven.com to license an upgraded key. Thank you!</Instructions>  <YieldNumber>6</YieldNumber>  <YieldUnit>Servings</YieldUnit>  <TotalMinutes xsi:nil='true' />  <ActiveMinutes xsi:nil='true' />  <NutritionInfo>    <SingularYieldUnit>Paid API plan required for nutrition.</SingularYieldUnit>    <TotalCalories>0</TotalCalories>    <TotalFat>0</TotalFat>    <CaloriesFromFat>0</CaloriesFromFat>    <TotalFatPct>0</TotalFatPct>    <SatFat>0</SatFat>    <SatFatPct>0</SatFatPct>    <MonoFat>0</MonoFat>    <PolyFat>0</PolyFat>    <TransFat>0</TransFat>    <Cholesterol>0</Cholesterol>    <CholesterolPct>0</CholesterolPct>    <Sodium>0</Sodium>    <SodiumPct>0</SodiumPct>    <Potassium>0</Potassium>    <PotassiumPct>0</PotassiumPct>    <TotalCarbs>0</TotalCarbs>    <TotalCarbsPct>0</TotalCarbsPct>    <DietaryFiber>0</DietaryFiber>    <DietaryFiberPct>0</DietaryFiberPct>    <Sugar>0</Sugar>    <Protein>0</Protein>    <ProteinPct>0</ProteinPct>  </NutritionInfo>  <IsPrivate>false</IsPrivate>  <CreationDate>2015-04-27T15:56:42.733Z</CreationDate>  <LastModified>2015-04-27T10:57:22.247Z</LastModified>  <IsBookmark>true</IsBookmark>  <BookmarkURL>http://www.halfbakedharvest.com/smoky-3-cheese-fondue-toasted-garlic-buttered-croissants/</BookmarkURL>  <BookmarkSiteLogo />  <BookmarkImageURL>http://www.halfbakedharvest.com/wp-content/uploads/2015/02/Smoky-3-Cheese-Fondue-with-Toasted-Garlic-Buttered-Croissants-1.jpg</BookmarkImageURL>  <IsRecipeScan xsi:nil='true' />  <MenuCount>0</MenuCount>  <NotesCount>0</NotesCount>  <AllCategoriesText />  <IsSponsored>false</IsSponsored>  <VariantOfRecipeID xsi:nil='true' />  <Collection />  <AdminBoost xsi:nil='true' />  <VerifiedDateTime xsi:nil='true' />  <MaxImageSquare>512</MaxImageSquare>  <ImageSquares>    <int>512</int>    <int>480</int>    <int>320</int>    <int>256</int>    <int>200</int>    <int>128</int>    <int>120</int>    <int>64</int>    <int>48</int>    <int>36</int>  </ImageSquares>  <HeroPhotoUrl>http://images.bigoven.com/image/upload/v1430150210/smoky3cheesefonduewithtoastedg-1b2abb.jpg</HeroPhotoUrl></Recipe>");



            int counter = 0;


            String ingredients = "";
            for (int i = 0; i < xmlDocument.getElementsByTagName("ingredient").getLength(); i++) {
                ingredients += xmlDocument.getElementsByTagName("ingredient").item(i);
            }

            HashMap resultMap = new HashMap<String, HashMap<String, String>>();

            HashMap header = new HashMap<String, String>();
            header.put("title", "title");
            header.put("version", "version");
            header.put("href", "href");
            resultMap.put("header", header);

            HashMap subResultMap = new HashMap<String, String>();
            subResultMap.put("title", "title");
            subResultMap.put("resultHref", "resultHref");
            subResultMap.put("ingredients", ingredients);
            subResultMap.put("thumbnail", "thumbnail");
            resultMap.put(Integer.toString(counter++), subResultMap);

            return resultMap;


        } catch (SAXParseException e){
            Log.v(TAG, Integer.toString(e.getColumnNumber()));
            Log.v(TAG, Integer.toString(e.getLineNumber()));

        } catch (Exception e){
            Log.v(TAG, e.toString());
        }

        return new HashMap<String, HashMap<String, String>>();
    }


    public Document loadXMLFromString(String xml) throws Exception
    {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource inputSource = new InputSource(new StringReader(xml));
        inputSource.setCharacterStream(new StringReader(xml));
        Document document = builder.parse(inputSource);
        return document;

    }
}
