<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="structure/meta.jsp" %>
    <title>Edit user</title>
    <%@ include file="structure/header.jsp" %>
</head>
<body>
<%@ include file="structure/beans.jsp" %>
${user.verifyAcces(pageContext.request,pageContext.response)}
<%@ include file="structure/statusbar.jsp" %>

<jsp:useBean id="country" class="com.stn.helpers.CountryHelper"/>
<c:set var="countries" value='${country.getCountries()}'/>

<table class="black" style="margin-top: 8pt; width: 570pt">

    <tr>
        <td class="center"><h1>Profile Settings</h1></td>
    </tr>

    <tr>
        <td class="center">

            <table class="black" border="1" style="margin-top: 5pt; width: 90%; border-collapse: collapse;">
                <form name="edit" action="EditUserProcess" method="post">
                    <tr>
                        <th colspan="2" style="text-align: left; padding: 3pt">Profile Settings</th>
                    </tr>
                    <tr>
                        <td class='row2' style="text-align: left;">Username</td>
                        <td class='row3' style="text-align: left">
                            <input style="height: 9pt" type="text" name="usernamer" value="${userInfo.getUserName()}" size="35" disabled>
                            <input type="hidden" name="username" value="${userInfo.getUserName()}">
                        </td>
                    </tr>
                    <tr>
                        <td class='row2' style="text-align: left;">First Name</td>
                        <td class='row3' style="text-align: left"><input style="height: 9pt" type="text" name="firstname" value="${userInfo.getFirstName()}" size="35"></td>
                    </tr>
                    <tr>
                        <td class='row2' style="text-align: left;">Last Name</td>
                        <td class='row3' style="text-align: left"><input style="height: 9pt" type="text" name="lastname" value="${userInfo.getLastName()}" size="35"></td>
                    </tr>
                    <tr>
                        <td class='row2' style="text-align: left;">Country</td>
                        <td class='row3' style="text-align: left">
                            <select name='country'>

                                 <option
                                 <c:if test="${userInfo.getCountryId() == 0}">selected</c:if>
                                         value="0">--</option>
                             <c:forEach items="${countries}" var="c">
                                 <option value="${c.getCountryId()}"
                                         <c:if test="${userInfo.getCountryId() == c.getCountryId()}">selected</c:if>
                                 >${c.getCountryName()}</option>
                             </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class='row2' style="text-align: left;">Gender</td>
                        <td class='row3' style="text-align: left;">
                            <input type="radio" name="gender" value="0" style="vertical-align: middle;"
                            <c:if test="${userInfo.getGender() == 0}">
                            checked
                            </c:if> >
                            <label style="vertical-align: middle;">Unknown</label>

                            <input type="radio" name="gender" value="1" style="vertical-align: middle;"
                            <c:if test="${userInfo.getGender() == 1}">
                                   checked
                            </c:if> >
                            <label style="vertical-align: middle;">Male</label>

                            <input type="radio" name="gender" value="2" style="vertical-align: middle;"
                            <c:if test="${userInfo.getGender() == 2}">
                                   checked
                            </c:if> >
                            <label style="vertical-align: middle;">Female</label>
                        </td>
                    </tr>
                    <tr>
                        <td class='row2' style="text-align: left;">Email</td>
                        <td class='row3' style="text-align: left">
                            <input style="height: 9pt" type="text" name="emailr" value="${userInfo.getEmail()}" size="35" disabled>
                            <input type="hidden" name="email" value="${userInfo.getEmail()}">
                        </td>
                    </tr>
                    <tr>
                        <td class='row2' style="text-align: left;">Avatar</td>
                        <td class='row3' style="text-align: left"><input style="height: 9pt" type="text" name="avatar" value="${userInfo.getAvatar()}" size="35"></td>
                    </tr>

                    <tr>
                        <td class='row2' style="text-align: left;">Name Anonymity</td>
                        <td class='row3' style="text-align: left;">
                            <input type="radio" name="anonymity" value="0" style="vertical-align: middle;"
                            <c:if test="${userInfo.getAnonymity() == 0}">
                                   checked
                            </c:if> >
                            <label style="vertical-align: middle;">No</label>

                            <input type="radio" name="anonymity" value="1" style="vertical-align: middle;"
                            <c:if test="${userInfo.getAnonymity() == 1}">
                                   checked
                            </c:if> >
                            <label style="vertical-align: middle;">Yes</label>
                        </td>
                    </tr>

                    <tr>
                        <td colspan="2" class="center" style="padding-top: 7pt; padding-bottom: 7pt; background-color: #2c2c2c"><input name="save_changes" type="submit" value="Save changes">${sessionScope.error}
                    </tr>
                </form>

                <form name="edit" action="ChangePasswordProcess" method="post">
                <tr>
                    <th colspan="2" style="text-align: left; padding: 3pt">Password Settings</th>
                </tr>
                <tr>
                    <td class='row2' style="text-align: left; width: 80pt;">Current Password</td>
                    <td class='row3' style="text-align: left;"><input style="height: 9pt" type="password" name="password0" value="" size="35"></td>
                </tr>
                <tr>
                    <td class='row2' style="text-align: left; width: 80pt;">New Password</td>
                    <td class='row3' style="text-align: left;"><input style="height: 9pt" type="password" name="password1" value="" size="35"></td>
                </tr>
                <tr>
                    <td class='row2' style="text-align: left; width: 80pt;">Confirm Password</td>
                    <td class='row3' style="text-align: left;"><input style="height: 9pt" type="password" name="password2" value="" size="35"></td>
                </tr>
                    <tr>
                        <td colspan="2" class="center" style="padding-top: 7pt; padding-bottom: 7pt; background-color: #2c2c2c"><input name="save_changes" type="submit" value="Update Password">${sessionScope.error2}
                    </tr>
                </form>


            </table>

        </td>
    </tr>
    <tr>
        <td>
            <br/><br/>
        </td>
    </tr>
</table>

<c:remove var="error" scope="session" />
<c:remove var="error2" scope="session" />

<%@ include file="structure/footer.jsp" %>

</body>
</html>
