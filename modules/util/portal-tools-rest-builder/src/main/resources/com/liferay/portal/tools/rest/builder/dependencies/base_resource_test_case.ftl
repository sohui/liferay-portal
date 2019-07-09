package ${configYAML.apiPackagePath}.resource.${escapedVersion}.test;

<#list allSchemas?keys as schemaName>
	import ${configYAML.apiPackagePath}.client.dto.${escapedVersion}.${schemaName};
	import ${configYAML.apiPackagePath}.client.resource.${escapedVersion}.${schemaName}Resource;
	import ${configYAML.apiPackagePath}.client.serdes.${escapedVersion}.${schemaName}SerDes;
</#list>

import ${configYAML.apiPackagePath}.client.http.HttpInvoker;
import ${configYAML.apiPackagePath}.client.pagination.Page;
import ${configYAML.apiPackagePath}.client.pagination.Pagination;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import com.liferay.petra.function.UnsafeTriConsumer;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.vulcan.resource.EntityModelResource;

import java.io.File;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Generated;

import javax.ws.rs.core.MultivaluedHashMap;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang.time.DateUtils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author ${configYAML.author}
 * @generated
 */
@Generated("")
public abstract class Base${schemaName}ResourceTestCase {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule = new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		_dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	}

	@Before
	public void setUp() throws Exception {
		irrelevantGroup = GroupTestUtil.addGroup();
		testGroup = GroupTestUtil.addGroup();

		testCompany = CompanyLocalServiceUtil.getCompany(testGroup.getCompanyId());

		_${schemaVarName}Resource.setContextCompany(testCompany);

		${schemaName}Resource.Builder builder = ${schemaName}Resource.builder();

		${schemaVarName}Resource = builder.locale(
			LocaleUtil.getDefault()
		).build();
	}

	@After
	public void tearDown() throws Exception {
		GroupTestUtil.deleteGroup(irrelevantGroup);
		GroupTestUtil.deleteGroup(testGroup);
	}

	@Test
	public void testClientSerDesToDTO() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				enable(SerializationFeature.INDENT_OUTPUT);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};

		${schemaName} ${schemaVarName}1 = random${schemaName}();

		String json = objectMapper.writeValueAsString(${schemaVarName}1);

		${schemaName} ${schemaVarName}2 = ${schemaName}SerDes.toDTO(json);

		Assert.assertTrue(equals(${schemaVarName}1, ${schemaVarName}2));
	}

	@Test
	public void testClientSerDesToJSON() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};

		${schemaName} ${schemaVarName} = random${schemaName}();

		String json1 = objectMapper.writeValueAsString(${schemaVarName});
		String json2 = ${schemaName}SerDes.toJSON(${schemaVarName});

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	<#assign properties = freeMarkerTool.getDTOProperties(configYAML, openAPIYAML, schema) />

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		${schemaName} ${schemaVarName} = random${schemaName}();

		<#list properties?keys as propertyName>
			<#if stringUtil.equals(properties[propertyName], "String")>
				${schemaVarName}.set${propertyName?cap_first}(regex);
			</#if>
		</#list>

		String json = ${schemaName}SerDes.toJSON(${schemaVarName});

		Assert.assertFalse(json.contains(regex));

		${schemaVarName} = ${schemaName}SerDes.toDTO(json);

		<#list properties?keys as propertyName>
			<#if stringUtil.equals(properties[propertyName], "String")>
				Assert.assertEquals(regex, ${schemaVarName}.get${propertyName?cap_first}());
			</#if>
		</#list>
	}

	<#assign
		enumSchemas = freeMarkerTool.getDTOEnumSchemas(openAPIYAML, schema)
		generateGetMultipartFilesMethod = false
		javaMethodSignatures = freeMarkerTool.getResourceTestCaseJavaMethodSignatures(configYAML, openAPIYAML, schemaName)
		properties = freeMarkerTool.getDTOProperties(configYAML, openAPIYAML, schema)
	/>

	<#list javaMethodSignatures as javaMethodSignature>
		<#assign
			arguments = freeMarkerTool.getResourceTestCaseArguments(javaMethodSignature.javaMethodParameters)
			parameters = freeMarkerTool.getResourceTestCaseParameters(javaMethodSignature.javaMethodParameters, openAPIYAML, javaMethodSignature.operation, false)
		/>

		<#if freeMarkerTool.hasHTTPMethod(javaMethodSignature, "delete")>
			@Test
			public void test${javaMethodSignature.methodName?cap_first}() throws Exception {
				<#if properties?keys?seq_contains("id")>
					${schemaName} ${schemaVarName} = test${javaMethodSignature.methodName?cap_first}_add${schemaName}();

					assertHttpResponseStatusCode(204, ${schemaVarName}Resource.${javaMethodSignature.methodName}HttpResponse(

					<#list javaMethodSignature.javaMethodParameters as javaMethodParameter>
						<#if !javaMethodParameter?is_first>
							,
						</#if>

						<#if freeMarkerTool.isPathParameter(javaMethodParameter, javaMethodSignature.operation)>
							<#if stringUtil.equals(javaMethodParameter.parameterName, schemaVarName + "Id")>
								${schemaVarName}.getId()
							<#else>
								null
							</#if>
						<#else>
							null
						</#if>
					</#list>

					));

					<#if freeMarkerTool.hasJavaMethodSignature(javaMethodSignatures, "get" + javaMethodSignature.methodName?remove_beginning("delete"))>
						assertHttpResponseStatusCode(404, ${schemaVarName}Resource.get${javaMethodSignature.methodName?remove_beginning("delete")}HttpResponse(${schemaVarName}.getId()));

						assertHttpResponseStatusCode(404, ${schemaVarName}Resource.get${javaMethodSignature.methodName?remove_beginning("delete")}HttpResponse(0L));
					</#if>
				<#else>
					Assert.assertTrue(true);
				</#if>
			}

			<#if properties?keys?seq_contains("id")>
				protected ${schemaName} test${javaMethodSignature.methodName?cap_first}_add${schemaName}() throws Exception {
					<#if freeMarkerTool.hasPostSchemaJavaMethodSignature(javaMethodSignatures, "siteId", schemaName)>
						<#assign postSchemaJavaMethodSignature = freeMarkerTool.getPostSchemaJavaMethodSignature(javaMethodSignatures, "siteId", schemaName) />

						return ${schemaVarName}Resource.postSite${schemaName}(testGroup.getGroupId(), random${schemaName}()

						<#if freeMarkerTool.hasRequestBodyMediaType(postSchemaJavaMethodSignature, "multipart/form-data")>
							<#assign generateGetMultipartFilesMethod = true />

							, getMultipartFiles()
						</#if>

						);
					<#else>
						throw new UnsupportedOperationException("This method needs to be implemented");
					</#if>
				}
			</#if>
		<#elseif freeMarkerTool.hasHTTPMethod(javaMethodSignature, "get") && javaMethodSignature.returnType?contains("Page<")>
			<#if (javaMethodSignature.javaMethodParameters?size == 0) || stringUtil.equals(javaMethodSignature.javaMethodParameters[0].parameterName, "filter") || stringUtil.equals(javaMethodSignature.javaMethodParameters[0].parameterName, "pagination") || stringUtil.equals(javaMethodSignature.javaMethodParameters[0].parameterName, "sorts")>
				@Test
				public void test${javaMethodSignature.methodName?cap_first}() throws Exception {
					Assert.assertTrue(true);
				}
			<#else>
				@Test
				public void test${javaMethodSignature.methodName?cap_first}() throws Exception {
					Page<${schemaName}> page = ${schemaVarName}Resource.${javaMethodSignature.methodName}(

					<#list javaMethodSignature.javaMethodParameters as javaMethodParameter>
						<#if !javaMethodParameter?is_first>
							,
						</#if>

						<#if stringUtil.equals(javaMethodParameter.parameterName, "pagination")>
							Pagination.of(1, 2)
						<#elseif freeMarkerTool.isPathParameter(javaMethodParameter, javaMethodSignature.operation)>
							test${javaMethodSignature.methodName?cap_first}_get${javaMethodParameter.parameterName?cap_first}()
						<#elseif stringUtil.equals(javaMethodParameter.parameterType, "java.lang.String")>
							RandomTestUtil.randomString()
						<#elseif stringUtil.equals(javaMethodParameter.parameterType, "boolean")>
							RandomTestUtil.randomBoolean();
						<#elseif stringUtil.equals(javaMethodParameter.parameterType, "double")>
							RandomTestUtil.randomDouble();
						<#elseif stringUtil.equals(javaMethodParameter.parameterType, "long")>
							RandomTestUtil.randomLong();
						<#elseif stringUtil.equals(javaMethodParameter.parameterType, "java.util.Date")>
							RandomTestUtil.nextDate();
						<#else>
							null
						</#if>
					</#list>

					);

					Assert.assertEquals(0, page.getTotalCount());

					<#list javaMethodSignature.pathJavaMethodParameters as javaMethodParameter>
						${javaMethodParameter.parameterType} ${javaMethodParameter.parameterName} = test${javaMethodSignature.methodName?cap_first}_get${javaMethodParameter.parameterName?cap_first}();
						${javaMethodParameter.parameterType} irrelevant${javaMethodParameter.parameterName?cap_first} = test${javaMethodSignature.methodName?cap_first}_getIrrelevant${javaMethodParameter.parameterName?cap_first}();
					</#list>

					<#if freeMarkerTool.hasPathParameter(javaMethodSignature)>
						if (<#list javaMethodSignature.pathJavaMethodParameters as javaMethodParameter>
								<#if !javaMethodParameter?is_first>
									&&
								</#if>

								(irrelevant${javaMethodParameter.parameterName?cap_first} != null)
							</#list>) {

							${schemaName} irrelevant${schemaName} = test${javaMethodSignature.methodName?cap_first}_add${schemaName}(

							<#list javaMethodSignature.pathJavaMethodParameters as javaMethodParameter>
								irrelevant${javaMethodParameter.parameterName?cap_first},
							</#list>

							randomIrrelevant${schemaName}());

							page = ${schemaVarName}Resource.${javaMethodSignature.methodName}(

							<#list javaMethodSignature.javaMethodParameters as javaMethodParameter>
								<#if !javaMethodParameter?is_first>
									,
								</#if>

								<#if stringUtil.equals(javaMethodParameter.parameterName, "pagination")>
									Pagination.of(1, 2)
								<#elseif freeMarkerTool.isPathParameter(javaMethodParameter, javaMethodSignature.operation)>
									irrelevant${javaMethodParameter.parameterName?cap_first}
								<#else>
									null
								</#if>
							</#list>

							);

							Assert.assertEquals(1, page.getTotalCount());

							assertEquals(Arrays.asList(irrelevant${schemaName}), (List<${schemaName}>)page.getItems());
							assertValid(page);
						}
					</#if>

					${schemaName} ${schemaVarName}1 = test${javaMethodSignature.methodName?cap_first}_add${schemaName}(

					<#list javaMethodSignature.pathJavaMethodParameters as javaMethodParameter>
						${javaMethodParameter.parameterName},
					</#list>

					random${schemaName}());

					${schemaName} ${schemaVarName}2 = test${javaMethodSignature.methodName?cap_first}_add${schemaName}(

					<#list javaMethodSignature.pathJavaMethodParameters as javaMethodParameter>
						${javaMethodParameter.parameterName},
					</#list>

					random${schemaName}());

					page = ${schemaVarName}Resource.${javaMethodSignature.methodName}(

					<#list javaMethodSignature.javaMethodParameters as javaMethodParameter>
						<#if !javaMethodParameter?is_first>
							,
						</#if>

						<#if stringUtil.equals(javaMethodParameter.parameterName, "pagination")>
							Pagination.of(1, 2)
						<#elseif freeMarkerTool.isPathParameter(javaMethodParameter, javaMethodSignature.operation)>
							${javaMethodParameter.parameterName}
						<#else>
							null
						</#if>
					</#list>

					);

					Assert.assertEquals(2, page.getTotalCount());

					assertEqualsIgnoringOrder(Arrays.asList(${schemaVarName}1, ${schemaVarName}2), (List<${schemaName}>)page.getItems());
					assertValid(page);
				}

				<#if parameters?contains("Filter filter")>
					@Test
					public void test${javaMethodSignature.methodName?cap_first}WithFilterDateTimeEquals() throws Exception {
						List<EntityField> entityFields = getEntityFields(EntityField.Type.DATE_TIME);

						if (entityFields.isEmpty()) {
							return;
						}

						<#list javaMethodSignature.pathJavaMethodParameters as javaMethodParameter>
							${javaMethodParameter.parameterType} ${javaMethodParameter.parameterName} = test${javaMethodSignature.methodName?cap_first}_get${javaMethodParameter.parameterName?cap_first}();
						</#list>

						${schemaName} ${schemaVarName}1 = random${schemaName}();

						${schemaVarName}1 = test${javaMethodSignature.methodName?cap_first}_add${schemaName}(

						<#list javaMethodSignature.pathJavaMethodParameters as javaMethodParameter>
							${javaMethodParameter.parameterName},
						</#list>

						${schemaVarName}1);

						for (EntityField entityField : entityFields) {
							Page<${schemaName}> page = ${schemaVarName}Resource.${javaMethodSignature.methodName}(

							<#list javaMethodSignature.javaMethodParameters as javaMethodParameter>
								<#if !javaMethodParameter?is_first>
									,
								</#if>

								<#if stringUtil.equals(javaMethodParameter.parameterName, "filter")>
									getFilterString(entityField, "between", ${schemaVarName}1)
								<#elseif stringUtil.equals(javaMethodParameter.parameterName, "pagination")>
									Pagination.of(1, 2)
								<#elseif freeMarkerTool.isPathParameter(javaMethodParameter, javaMethodSignature.operation)>
									${javaMethodParameter.parameterName}
								<#else>
									null
								</#if>
							</#list>

							);

							assertEquals(Collections.singletonList(${schemaVarName}1), (List<${schemaName}>)page.getItems());
						}
					}

					@Test
					public void test${javaMethodSignature.methodName?cap_first}WithFilterStringEquals() throws Exception {
						List<EntityField> entityFields = getEntityFields(EntityField.Type.STRING);

						if (entityFields.isEmpty()) {
							return;
						}

						<#list javaMethodSignature.pathJavaMethodParameters as javaMethodParameter>
							${javaMethodParameter.parameterType} ${javaMethodParameter.parameterName} = test${javaMethodSignature.methodName?cap_first}_get${javaMethodParameter.parameterName?cap_first}();
						</#list>

						${schemaName} ${schemaVarName}1 = test${javaMethodSignature.methodName?cap_first}_add${schemaName}(

						<#list javaMethodSignature.pathJavaMethodParameters as javaMethodParameter>
							${javaMethodParameter.parameterName},
						</#list>

						random${schemaName}());

						@SuppressWarnings("PMD.UnusedLocalVariable")
						${schemaName} ${schemaVarName}2 = test${javaMethodSignature.methodName?cap_first}_add${schemaName}(

						<#list javaMethodSignature.pathJavaMethodParameters as javaMethodParameter>
							${javaMethodParameter.parameterName},
						</#list>

						random${schemaName}());

						for (EntityField entityField : entityFields) {
							Page<${schemaName}> page = ${schemaVarName}Resource.${javaMethodSignature.methodName}(

							<#list javaMethodSignature.javaMethodParameters as javaMethodParameter>
								<#if !javaMethodParameter?is_first>
									,
								</#if>

								<#if stringUtil.equals(javaMethodParameter.parameterName, "filter")>
									getFilterString(entityField, "eq", ${schemaVarName}1)
								<#elseif stringUtil.equals(javaMethodParameter.parameterName, "pagination")>
									Pagination.of(1, 2)
								<#elseif freeMarkerTool.isPathParameter(javaMethodParameter, javaMethodSignature.operation)>
									${javaMethodParameter.parameterName}
								<#else>
									null
								</#if>
							</#list>

							);

							assertEquals(Collections.singletonList(${schemaVarName}1), (List<${schemaName}>)page.getItems());
						}
					}
				</#if>

				<#if parameters?contains("Pagination pagination")>
					@Test
					public void test${javaMethodSignature.methodName?cap_first}WithPagination() throws Exception {
						<#list javaMethodSignature.pathJavaMethodParameters as javaMethodParameter>
							${javaMethodParameter.parameterType} ${javaMethodParameter.parameterName} = test${javaMethodSignature.methodName?cap_first}_get${javaMethodParameter.parameterName?cap_first}();
						</#list>

						${schemaName} ${schemaVarName}1 = test${javaMethodSignature.methodName?cap_first}_add${schemaName}(

						<#list javaMethodSignature.pathJavaMethodParameters as javaMethodParameter>
							${javaMethodParameter.parameterName},
						</#list>

						random${schemaName}());

						${schemaName} ${schemaVarName}2 = test${javaMethodSignature.methodName?cap_first}_add${schemaName}(

						<#list javaMethodSignature.pathJavaMethodParameters as javaMethodParameter>
							${javaMethodParameter.parameterName},
						</#list>

						random${schemaName}());

						${schemaName} ${schemaVarName}3 = test${javaMethodSignature.methodName?cap_first}_add${schemaName}(

						<#list javaMethodSignature.pathJavaMethodParameters as javaMethodParameter>
							${javaMethodParameter.parameterName},
						</#list>

						random${schemaName}());

						Page<${schemaName}> page1 = ${schemaVarName}Resource.${javaMethodSignature.methodName}(

						<#list javaMethodSignature.javaMethodParameters as javaMethodParameter>
							<#if !javaMethodParameter?is_first>
								,
							</#if>

							<#if stringUtil.equals(javaMethodParameter.parameterName, "pagination")>
								Pagination.of(1, 2)
							<#elseif freeMarkerTool.isPathParameter(javaMethodParameter, javaMethodSignature.operation)>
								${javaMethodParameter.parameterName}
							<#else>
								null
							</#if>
						</#list>

						);

						List<${schemaName}> ${schemaVarNames}1 = (List<${schemaName}>)page1.getItems();

						Assert.assertEquals(${schemaVarNames}1.toString(), 2, ${schemaVarNames}1.size());

						Page<${schemaName}> page2 = ${schemaVarName}Resource.${javaMethodSignature.methodName}(

						<#list javaMethodSignature.javaMethodParameters as javaMethodParameter>
							<#if !javaMethodParameter?is_first>
								,
							</#if>

							<#if stringUtil.equals(javaMethodParameter.parameterName, "pagination")>
								Pagination.of(2, 2)
							<#elseif freeMarkerTool.isPathParameter(javaMethodParameter, javaMethodSignature.operation)>
								${javaMethodParameter.parameterName}
							<#else>
								null
							</#if>
						</#list>

						);

						Assert.assertEquals(3, page2.getTotalCount());

						List<${schemaName}> ${schemaVarNames}2 = (List<${schemaName}>)page2.getItems();

						Assert.assertEquals(${schemaVarNames}2.toString(), 1, ${schemaVarNames}2.size());

						Page<${schemaName}> page3 = ${schemaVarName}Resource.${javaMethodSignature.methodName}(

						<#list javaMethodSignature.javaMethodParameters as javaMethodParameter>
							<#if !javaMethodParameter?is_first>
								,
							</#if>

							<#if stringUtil.equals(javaMethodParameter.parameterName, "pagination")>
								Pagination.of(1, 3)
							<#elseif freeMarkerTool.isPathParameter(javaMethodParameter, javaMethodSignature.operation)>
								${javaMethodParameter.parameterName}
							<#else>
								null
							</#if>
						</#list>

						);

					assertEqualsIgnoringOrder(
							Arrays.asList(${schemaVarName}1, ${schemaVarName}2, ${schemaVarName}3),
							(List<${schemaName}>)page3.getItems());
					}
				</#if>

				<#if parameters?contains("Sort[] sorts")>
					@Test
					public void test${javaMethodSignature.methodName?cap_first}WithSortDateTime() throws Exception {
						test${javaMethodSignature.methodName?cap_first}WithSort(
							EntityField.Type.DATE_TIME,
							(entityField, ${schemaVarName}1, ${schemaVarName}2) -> {
								BeanUtils.setProperty(${schemaVarName}1, entityField.getName(), DateUtils.addMinutes(new Date(), -2));
							});
					}

					@Test
					public void test${javaMethodSignature.methodName?cap_first}WithSortInteger() throws Exception {
						test${javaMethodSignature.methodName?cap_first}WithSort(
							EntityField.Type.INTEGER,
							(entityField, ${schemaVarName}1, ${schemaVarName}2) -> {
								BeanUtils.setProperty(${schemaVarName}1, entityField.getName(), 0);
								BeanUtils.setProperty(${schemaVarName}2, entityField.getName(), 1);
							});
					}

					@Test
					public void test${javaMethodSignature.methodName?cap_first}WithSortString() throws Exception {
						test${javaMethodSignature.methodName?cap_first}WithSort(
							EntityField.Type.STRING,
							(entityField, ${schemaVarName}1, ${schemaVarName}2) -> {

								Class clazz = ${schemaVarName}1.getClass();

								Method method = clazz.getMethod( "get" + StringUtil.upperCaseFirstLetter(entityField.getName()));

								Class<?> returnType = method.getReturnType();

								if (returnType.isAssignableFrom(Map.class)) {
									BeanUtils.setProperty(${schemaVarName}1, entityField.getName(), Collections.singletonMap("Aaa", "Aaa"));
									BeanUtils.setProperty(${schemaVarName}2, entityField.getName(), Collections.singletonMap("Bbb", "Bbb"));
								}
								else {
									BeanUtils.setProperty(${schemaVarName}1, entityField.getName(), "Aaa");
									BeanUtils.setProperty(${schemaVarName}2, entityField.getName(), "Bbb");
								}
							});
					}

					protected void test${javaMethodSignature.methodName?cap_first}WithSort(EntityField.Type type, UnsafeTriConsumer<EntityField, ${schemaName}, ${schemaName}, Exception> unsafeTriConsumer) throws Exception {
						List<EntityField> entityFields = getEntityFields(type);

						if (entityFields.isEmpty()) {
							return;
						}

						<#list javaMethodSignature.pathJavaMethodParameters as javaMethodParameter>
							${javaMethodParameter.parameterType} ${javaMethodParameter.parameterName} = test${javaMethodSignature.methodName?cap_first}_get${javaMethodParameter.parameterName?cap_first}();
						</#list>

						${schemaName} ${schemaVarName}1 = random${schemaName}();
						${schemaName} ${schemaVarName}2 = random${schemaName}();

						for (EntityField entityField : entityFields) {
							unsafeTriConsumer.accept(entityField, ${schemaVarName}1, ${schemaVarName}2);
						}

						${schemaVarName}1 = test${javaMethodSignature.methodName?cap_first}_add${schemaName}(

						<#list javaMethodSignature.pathJavaMethodParameters as javaMethodParameter>
							${javaMethodParameter.parameterName},
						</#list>

						${schemaVarName}1);

						${schemaVarName}2 = test${javaMethodSignature.methodName?cap_first}_add${schemaName}(

						<#list javaMethodSignature.pathJavaMethodParameters as javaMethodParameter>
							${javaMethodParameter.parameterName},
						</#list>

						${schemaVarName}2);

						for (EntityField entityField : entityFields) {
							Page<${schemaName}> ascPage = ${schemaVarName}Resource.${javaMethodSignature.methodName}(

							<#list javaMethodSignature.javaMethodParameters as javaMethodParameter>
								<#if !javaMethodParameter?is_first>
									,
								</#if>

								<#if stringUtil.equals(javaMethodParameter.parameterName, "pagination")>
									Pagination.of(1, 2)
								<#elseif stringUtil.equals(javaMethodParameter.parameterName, "sorts")>
									entityField.getName() + ":asc"
								<#elseif freeMarkerTool.isPathParameter(javaMethodParameter, javaMethodSignature.operation)>
									${javaMethodParameter.parameterName}
								<#else>
									null
								</#if>
							</#list>

							);

							assertEquals(Arrays.asList(${schemaVarName}1, ${schemaVarName}2), (List<${schemaName}>)ascPage.getItems());

							Page<${schemaName}> descPage = ${schemaVarName}Resource.${javaMethodSignature.methodName}(

							<#list javaMethodSignature.javaMethodParameters as javaMethodParameter>
								<#if !javaMethodParameter?is_first>
									,
								</#if>

								<#if stringUtil.equals(javaMethodParameter.parameterName, "pagination")>
									Pagination.of(1, 2)
								<#elseif stringUtil.equals(javaMethodParameter.parameterName, "sorts")>
									entityField.getName() + ":desc"
								<#elseif freeMarkerTool.isPathParameter(javaMethodParameter, javaMethodSignature.operation)>
									${javaMethodParameter.parameterName}
								<#else>
									null
								</#if>
							</#list>

							);

							assertEquals(Arrays.asList(${schemaVarName}2, ${schemaVarName}1), (List<${schemaName}>)descPage.getItems());
						}
					}
				</#if>

				protected ${schemaName} test${javaMethodSignature.methodName?cap_first}_add${schemaName}(
						<#list javaMethodSignature.pathJavaMethodParameters as javaMethodParameter>
							${javaMethodParameter.parameterType} ${javaMethodParameter.parameterName},
						</#list>

						${schemaName} ${schemaVarName}
					) throws Exception {

					<#if (javaMethodSignature.pathJavaMethodParameters?size == 1)>
						<#assign firstPathJavaMethodParameter = javaMethodSignature.pathJavaMethodParameters[0] />

						<#if freeMarkerTool.hasPostSchemaJavaMethodSignature(javaMethodSignatures, firstPathJavaMethodParameter.parameterName, schemaName)>
							<#assign postSchemaJavaMethodSignature = freeMarkerTool.getPostSchemaJavaMethodSignature(javaMethodSignatures, firstPathJavaMethodParameter.parameterName, schemaName) />

							return ${schemaVarName}Resource.${postSchemaJavaMethodSignature.methodName}(${firstPathJavaMethodParameter.parameterName}, ${schemaVarName}

							<#if freeMarkerTool.hasRequestBodyMediaType(postSchemaJavaMethodSignature, "multipart/form-data")>
								<#assign generateGetMultipartFilesMethod = true />

								, getMultipartFiles()
							</#if>

							);
						<#else>
							throw new UnsupportedOperationException("This method needs to be implemented");
						</#if>
					<#else>
						throw new UnsupportedOperationException("This method needs to be implemented");
					</#if>
				}

				<#list javaMethodSignature.pathJavaMethodParameters as javaMethodParameter>
					protected ${javaMethodParameter.parameterType} test${javaMethodSignature.methodName?cap_first}_get${javaMethodParameter.parameterName?cap_first}() throws Exception {
						<#if stringUtil.equals(javaMethodParameter.parameterName, "siteId")>
							return testGroup.getGroupId();
						<#else>
							throw new UnsupportedOperationException("This method needs to be implemented");
						</#if>
					}

					protected ${javaMethodParameter.parameterType} test${javaMethodSignature.methodName?cap_first}_getIrrelevant${javaMethodParameter.parameterName?cap_first}() throws Exception {
						<#if stringUtil.equals(javaMethodParameter.parameterName, "siteId")>
							return irrelevantGroup.getGroupId();
						<#else>
							return null;
						</#if>
					}
				</#list>
			</#if>
		<#elseif freeMarkerTool.hasHTTPMethod(javaMethodSignature, "get") && javaMethodSignature.returnType?ends_with(schemaName)>
			@Test
			public void test${javaMethodSignature.methodName?cap_first}() throws Exception {
				<#if properties?keys?seq_contains("id")>
					${schemaName} post${schemaName} = test${javaMethodSignature.methodName?cap_first}_add${schemaName}();

					${schemaName} get${schemaName} = ${schemaVarName}Resource.${javaMethodSignature.methodName}(

					<#list javaMethodSignature.javaMethodParameters as javaMethodParameter>
						<#if !javaMethodParameter?is_first>
							,
						</#if>

						<#if stringUtil.equals(javaMethodParameter.parameterName, "pagination")>
							Pagination.of(1, 2)
						<#elseif freeMarkerTool.isPathParameter(javaMethodParameter, javaMethodSignature.operation)>
							post${schemaName}.

							<#if stringUtil.equals(javaMethodParameter.parameterName, schemaVarName + "Id")>
								getId
							<#else>
								get${javaMethodParameter.parameterName?cap_first}
							</#if>

							()
						<#else>
							null
						</#if>
					</#list>

					);

					assertEquals(post${schemaName}, get${schemaName});
					assertValid(get${schemaName});
				<#else>
					Assert.assertTrue(true);
				</#if>
			}

			<#if properties?keys?seq_contains("id")>
				protected ${schemaName} test${javaMethodSignature.methodName?cap_first}_add${schemaName}() throws Exception {
					<#if freeMarkerTool.hasPostSchemaJavaMethodSignature(javaMethodSignatures, "siteId", schemaName)>
						<#assign postSchemaJavaMethodSignature = freeMarkerTool.getPostSchemaJavaMethodSignature(javaMethodSignatures, "siteId", schemaName) />

						return ${schemaVarName}Resource.postSite${schemaName}(testGroup.getGroupId(), random${schemaName}()

						<#if freeMarkerTool.hasRequestBodyMediaType(postSchemaJavaMethodSignature, "multipart/form-data")>
							<#assign generateGetMultipartFilesMethod = true />

							, getMultipartFiles()
						</#if>

						);
					<#else>
						throw new UnsupportedOperationException("This method needs to be implemented");
					</#if>
				}
			</#if>
		<#elseif freeMarkerTool.hasHTTPMethod(javaMethodSignature, "patch") && javaMethodSignature.returnType?ends_with(schemaName)>
			@Test
			public void test${javaMethodSignature.methodName?cap_first}() throws Exception {
				<#if !properties?keys?seq_contains("id")>
					Assert.assertTrue(true);
				<#else>
					${schemaName} post${schemaName} = test${javaMethodSignature.methodName?cap_first}_add${schemaName}();

					${schemaName} randomPatch${schemaName} = randomPatch${schemaName}();

					<#if freeMarkerTool.hasRequestBodyMediaType(javaMethodSignature, "multipart/form-data")>
						<#assign generateGetMultipartFilesMethod = true />

						Map<String, File> multipartFiles = getMultipartFiles();
					</#if>

					${schemaName} patch${schemaName} = ${schemaVarName}Resource.${javaMethodSignature.methodName}(post${schemaName}.getId(), randomPatch${schemaName}

					<#if freeMarkerTool.hasRequestBodyMediaType(javaMethodSignature, "multipart/form-data")>
						, multipartFiles
					</#if>

					);

					${schemaName} expectedPatch${schemaName} = (${schemaName})BeanUtils.cloneBean(post${schemaName});

					_beanUtilsBean.copyProperties(expectedPatch${schemaName}, randomPatch${schemaName});

					${schemaName} get${schemaName} = ${schemaVarName}Resource.get${schemaName}(patch${schemaName}.getId());

					assertEquals(expectedPatch${schemaName}, get${schemaName});
					assertValid(get${schemaName});

					<#if freeMarkerTool.hasRequestBodyMediaType(javaMethodSignature, "multipart/form-data")>
						assertValid(get${schemaName}, multipartFiles);
					</#if>
				</#if>
			}

			<#if properties?keys?seq_contains("id")>
				protected ${schemaName} test${javaMethodSignature.methodName?cap_first}_add${schemaName}() throws Exception {
					<#if freeMarkerTool.hasPostSchemaJavaMethodSignature(javaMethodSignatures, "siteId", schemaName)>
						<#assign postSchemaJavaMethodSignature = freeMarkerTool.getPostSchemaJavaMethodSignature(javaMethodSignatures, "siteId", schemaName) />

						return ${schemaVarName}Resource.postSite${schemaName}(testGroup.getGroupId(), random${schemaName}()

						<#if freeMarkerTool.hasRequestBodyMediaType(postSchemaJavaMethodSignature, "multipart/form-data")>
							, getMultipartFiles()
						</#if>

						);
					<#else>
						throw new UnsupportedOperationException("This method needs to be implemented");
					</#if>
				}
			</#if>
		<#elseif freeMarkerTool.hasHTTPMethod(javaMethodSignature, "post") && javaMethodSignature.returnType?ends_with(schemaName)>
			@Test
			public void test${javaMethodSignature.methodName?cap_first}() throws Exception {
				${schemaName} random${schemaName} = random${schemaName}();

				<#if freeMarkerTool.hasRequestBodyMediaType(javaMethodSignature, "multipart/form-data")>
					<#assign generateGetMultipartFilesMethod = true />

					Map<String, File> multipartFiles = getMultipartFiles();
				</#if>

				${schemaName} post${schemaName} = test${javaMethodSignature.methodName?cap_first}_add${schemaName}(random${schemaName}

				<#if freeMarkerTool.hasRequestBodyMediaType(javaMethodSignature, "multipart/form-data")>
					, multipartFiles
				</#if>

				);

				assertEquals(random${schemaName}, post${schemaName});
				assertValid(post${schemaName});

				<#if freeMarkerTool.hasRequestBodyMediaType(javaMethodSignature, "multipart/form-data")>
					assertValid(post${schemaName}, multipartFiles);
				</#if>
			}

			protected ${schemaName} test${javaMethodSignature.methodName?cap_first}_add${schemaName}(${schemaName} ${schemaVarName}

			<#if freeMarkerTool.hasRequestBodyMediaType(javaMethodSignature, "multipart/form-data")>
				, Map<String, File> multipartFiles
			</#if>

			) throws Exception {
				<#if (javaMethodSignature.pathJavaMethodParameters?size == 1)>
					<#assign
						firstPathJavaMethodParameter = javaMethodSignature.pathJavaMethodParameters[0]
						modifiedPathJavaMethodParameterName = firstPathJavaMethodParameter.parameterName?remove_beginning("parent")?remove_ending("Id")?cap_first
					/>

					<#if freeMarkerTool.hasPostSchemaJavaMethodSignature(javaMethodSignatures, firstPathJavaMethodParameter.parameterName, schemaName) && stringUtil.equals(javaMethodSignature.methodName, "post" + modifiedPathJavaMethodParameterName + schemaName)>
						return ${schemaVarName}Resource.post${modifiedPathJavaMethodParameterName}${schemaName}(testGet${modifiedPathJavaMethodParameterName}${schemaNames}Page_get<#if stringUtil.startsWith(firstPathJavaMethodParameter.parameterName, "parent")>Parent</#if>${modifiedPathJavaMethodParameterName}Id(), ${schemaVarName}

						<#if freeMarkerTool.hasRequestBodyMediaType(javaMethodSignature, "multipart/form-data")>
							, multipartFiles
						</#if>

						);
					<#else>
						throw new UnsupportedOperationException("This method needs to be implemented");
					</#if>
				<#else>
					throw new UnsupportedOperationException("This method needs to be implemented");
				</#if>
			}
		<#elseif freeMarkerTool.hasHTTPMethod(javaMethodSignature, "put") && javaMethodSignature.returnType?ends_with(schemaName)>
			@Test
			public void test${javaMethodSignature.methodName?cap_first}() throws Exception {
				<#if !properties?keys?seq_contains("id")>
					Assert.assertTrue(true);
				<#else>
					${schemaName} post${schemaName} = test${javaMethodSignature.methodName?cap_first}_add${schemaName}();

					${schemaName} random${schemaName} = random${schemaName}();

					<#if freeMarkerTool.hasRequestBodyMediaType(javaMethodSignature, "multipart/form-data")>
						<#assign generateGetMultipartFilesMethod = true />

						Map<String, File> multipartFiles = getMultipartFiles();
					</#if>

					${schemaName} put${schemaName} = ${schemaVarName}Resource.put${schemaName}(post${schemaName}.getId(), random${schemaName}

					<#if freeMarkerTool.hasRequestBodyMediaType(javaMethodSignature, "multipart/form-data")>
						, multipartFiles
					</#if>

					);

					assertEquals(random${schemaName}, put${schemaName});
					assertValid(put${schemaName});

					${schemaName} get${schemaName} = ${schemaVarName}Resource.get${schemaName}(put${schemaName}.getId());

					assertEquals(random${schemaName}, get${schemaName});
					assertValid(get${schemaName});

					<#if freeMarkerTool.hasRequestBodyMediaType(javaMethodSignature, "multipart/form-data")>
						assertValid(get${schemaName}, multipartFiles);
					</#if>
				</#if>
			}

			<#if properties?keys?seq_contains("id")>
				protected ${schemaName} test${javaMethodSignature.methodName?cap_first}_add${schemaName}() throws Exception {
					<#if freeMarkerTool.hasPostSchemaJavaMethodSignature(javaMethodSignatures, "siteId", schemaName)>
						<#assign postSchemaJavaMethodSignature = freeMarkerTool.getPostSchemaJavaMethodSignature(javaMethodSignatures, "siteId", schemaName) />

						return ${schemaVarName}Resource.postSite${schemaName}(testGroup.getGroupId(), random${schemaName}()

						<#if freeMarkerTool.hasRequestBodyMediaType(postSchemaJavaMethodSignature, "multipart/form-data")>
							, getMultipartFiles()
						</#if>

						);
					<#else>
						throw new UnsupportedOperationException("This method needs to be implemented");
					</#if>
				}
			</#if>
		<#else>
			@Test
			public void test${javaMethodSignature.methodName?cap_first}() throws Exception {
				Assert.assertTrue(true);
			}
		</#if>
	</#list>

	protected void assertHttpResponseStatusCode(int expectedHttpResponseStatusCode, HttpInvoker.HttpResponse actualHttpResponse) {
		Assert.assertEquals(expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(${schemaName} ${schemaVarName}1, ${schemaName} ${schemaVarName}2) {
		Assert.assertTrue(${schemaVarName}1 + " does not equal " + ${schemaVarName}2, equals(${schemaVarName}1, ${schemaVarName}2));
	}

	protected void assertEquals(List<${schemaName}> ${schemaVarNames}1, List<${schemaName}> ${schemaVarNames}2) {
		Assert.assertEquals(${schemaVarNames}1.size(), ${schemaVarNames}2.size());

		for (int i = 0; i < ${schemaVarNames}1.size(); i++) {
			${schemaName} ${schemaVarName}1 = ${schemaVarNames}1.get(i);
			${schemaName} ${schemaVarName}2 = ${schemaVarNames}2.get(i);

			assertEquals(${schemaVarName}1, ${schemaVarName}2);
		}
	}

	protected void assertEqualsIgnoringOrder(List<${schemaName}> ${schemaVarNames}1, List<${schemaName}> ${schemaVarNames}2) {
		Assert.assertEquals(${schemaVarNames}1.size(), ${schemaVarNames}2.size());

		for (${schemaName} ${schemaVarName}1 : ${schemaVarNames}1) {
			boolean contains = false;

			for (${schemaName} ${schemaVarName}2 : ${schemaVarNames}2) {
				if (equals(${schemaVarName}1, ${schemaVarName}2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(${schemaVarNames}2 + " does not contain " + ${schemaVarName}1, contains);
		}
	}

	protected void assertValid(${configYAML.apiPackagePath}.client.dto.${escapedVersion}.${schemaName} ${schemaVarName}) {
		boolean valid = true;

		<#if properties?keys?seq_contains("dateCreated")>
			if (${schemaVarName}.getDateCreated() == null) {
				valid = false;
			}
		</#if>

		<#if properties?keys?seq_contains("dateModified")>
			if (${schemaVarName}.getDateModified() == null) {
				valid = false;
			}
		</#if>

		<#if properties?keys?seq_contains("id")>
			if (${schemaVarName}.getId() == null) {
				valid = false;
			}
		</#if>

		<#if properties?keys?seq_contains("siteId")>
			if (!Objects.equals(${schemaVarName}.getSiteId(), testGroup.getGroupId())) {
				valid = false;
			}
		</#if>

		for (String additionalAssertFieldName : getAdditionalAssertFieldNames()) {
			<#list properties?keys as propertyName>
				<#if stringUtil.equals(propertyName, "dateCreated") ||
					 stringUtil.equals(propertyName, "dateModified") ||
					 stringUtil.equals(propertyName, "id") ||
					 stringUtil.equals(propertyName, "siteId")>

					 <#continue>
				</#if>

				if (Objects.equals("${propertyName}", additionalAssertFieldName)) {
					<#assign capitalizedPropertyName = propertyName?cap_first />

					<#if enumSchemas?keys?seq_contains(properties[propertyName])>
						<#assign capitalizedPropertyName = properties[propertyName] />
					</#if>

					if (${schemaVarName}.get${capitalizedPropertyName}() == null) {
						valid = false;
					}

					continue;
				}
			</#list>

			throw new IllegalArgumentException("Invalid additional assert field name " + additionalAssertFieldName);
		}

		Assert.assertTrue(valid);
	}

	<#if generateGetMultipartFilesMethod>
		protected void assertValid(${configYAML.apiPackagePath}.client.dto.${escapedVersion}.${schemaName} ${schemaVarName}, Map<String, File> multipartFiles) throws Exception {
			throw new UnsupportedOperationException("This method needs to be implemented");
		}
	</#if>

	protected void assertValid(Page<${schemaName}> page) {
		boolean valid = false;

		java.util.Collection<${schemaName}> ${schemaVarNames} = page.getItems();

		int size = ${schemaVarNames}.size();

		if ((page.getLastPage() > 0) && (page.getPage() > 0) && (page.getPageSize() > 0) && (page.getTotalCount() > 0) && (size > 0)) {
			valid = true;
		}

		Assert.assertTrue(valid);
	}

	protected String[] getAdditionalAssertFieldNames() {
		return new String[0];
	}

	protected String[] getIgnoredEntityFieldNames() {
		return new String[0];
	}

	protected boolean equals(${schemaName} ${schemaVarName}1, ${schemaName} ${schemaVarName}2) {
		if (${schemaVarName}1 == ${schemaVarName}2) {
			return true;
		}

		<#if properties?keys?seq_contains("siteId")>
			if (!Objects.equals(${schemaVarName}1.getSiteId(), ${schemaVarName}2.getSiteId())) {
				return false;
			}
		</#if>

		for (String additionalAssertFieldName : getAdditionalAssertFieldNames()) {
			<#list properties?keys as propertyName>
				<#if stringUtil.equals(propertyName, "siteId")>
					 <#continue>
				</#if>

				if (Objects.equals("${propertyName}", additionalAssertFieldName)) {
					<#assign capitalizedPropertyName = propertyName?cap_first />

					<#if enumSchemas?keys?seq_contains(properties[propertyName])>
						<#assign capitalizedPropertyName = properties[propertyName] />
					</#if>

					if (!Objects.deepEquals(${schemaVarName}1.get${capitalizedPropertyName}(), ${schemaVarName}2.get${capitalizedPropertyName}())) {
						return false;
					}

					continue;
				}
			</#list>

			throw new IllegalArgumentException("Invalid additional assert field name " + additionalAssertFieldName);
		}

		return true;
	}

	protected java.util.Collection<EntityField> getEntityFields() throws Exception {
		if (!(_${schemaVarName}Resource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException("Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource = (EntityModelResource)_${schemaVarName}Resource;

		EntityModel entityModel = entityModelResource.getEntityModel(new MultivaluedHashMap());

		Map<String, EntityField> entityFieldsMap = entityModel.getEntityFieldsMap();

		return entityFieldsMap.values();
	}

	protected List<EntityField> getEntityFields(EntityField.Type type) throws Exception {
		java.util.Collection<EntityField> entityFields = getEntityFields();

		Stream<EntityField> stream = entityFields.stream();

		return stream.filter(
			entityField -> Objects.equals(entityField.getType(), type) && !ArrayUtil.contains(getIgnoredEntityFieldNames(), entityField.getName())
		).collect(
			Collectors.toList()
		);
	}

	protected String getFilterString(EntityField entityField, String operator, ${schemaName} ${schemaVarName}) {
		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		<#list properties?keys as propertyName>
			if (entityFieldName.equals("${propertyName}")) {
				<#if stringUtil.equals(properties[propertyName], "Date")>
					if (operator.equals("between")) {
						sb = new StringBundler();

						sb.append("(");
						sb.append(entityFieldName);
						sb.append(" gt ");
						sb.append(_dateFormat.format(DateUtils.addSeconds(${schemaVarName}.get${propertyName?cap_first}(), -2)));
						sb.append(" and ");
						sb.append(entityFieldName);
						sb.append(" lt ");
						sb.append(_dateFormat.format(DateUtils.addSeconds(${schemaVarName}.get${propertyName?cap_first}(), 2)));
						sb.append(")");
					}
					else {
						sb.append(entityFieldName);

						sb.append(" ");
						sb.append(operator);
						sb.append(" ");

						sb.append(_dateFormat.format(${schemaVarName}.get${propertyName?cap_first}()));
					}

					return sb.toString();
				<#elseif stringUtil.equals(properties[propertyName], "String")>
					sb.append("'");
					sb.append(String.valueOf(${schemaVarName}.get${propertyName?cap_first}()));
					sb.append("'");

					return sb.toString();
				<#else>
					throw new IllegalArgumentException("Invalid entity field " + entityFieldName);
				</#if>
			}
		</#list>

		throw new IllegalArgumentException("Invalid entity field " + entityFieldName);
	}

	<#if generateGetMultipartFilesMethod>
		protected Map<String, File> getMultipartFiles() throws Exception {
			throw new UnsupportedOperationException("This method needs to be implemented");
		}
	</#if>

	protected ${schemaName} random${schemaName}() throws Exception {
		return new ${schemaName}() {
			{
				<#assign randomDataTypes = ["Boolean", "Double", "Long", "String"] />

				<#list properties?keys as propertyName>
					<#if stringUtil.equals(propertyName, "siteId")>
						${propertyName} = testGroup.getGroupId();
					<#elseif randomDataTypes?seq_contains(properties[propertyName])>
						${propertyName} = RandomTestUtil.random${properties[propertyName]}();
					<#elseif stringUtil.equals(properties[propertyName], "Date")>
						${propertyName} = RandomTestUtil.nextDate();
					</#if>
				</#list>
			}
		};
	}

	protected ${schemaName} randomIrrelevant${schemaName}() throws Exception {
		${schemaName} randomIrrelevant${schemaName} = random${schemaName}();

		<#if properties?keys?seq_contains("siteId")>
			randomIrrelevant${schemaName}.setSiteId(irrelevantGroup.getGroupId());
		</#if>

		return randomIrrelevant${schemaName};
	}

	protected ${schemaName} randomPatch${schemaName}() throws Exception {
		return random${schemaName}();
	}

	protected ${schemaName}Resource ${schemaVarName}Resource;
	protected Group irrelevantGroup;
	protected Company testCompany;
	protected Group testGroup;

	private static final Log _log = LogFactoryUtil.getLog(Base${schemaName}ResourceTestCase.class);

	private static BeanUtilsBean _beanUtilsBean = new BeanUtilsBean() {

		@Override
		public void copyProperty(Object bean, String name, Object value) throws IllegalAccessException, InvocationTargetException {
			if (value != null) {
				super.copyProperty(bean, name, value);
			}
		}

	};
	private static DateFormat _dateFormat;

	@Inject
	private ${configYAML.apiPackagePath}.resource.${escapedVersion}.${schemaName}Resource _${schemaVarName}Resource;

}