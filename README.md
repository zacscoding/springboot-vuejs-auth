## Spring boot + Vue.js auth sample  

> Get access token from API(backend) from POST MAN  

```aidl
http://localhost:3000/oauth/token

Authorization
- type : Basic Auth
- Username : myApp
- Password : pass

Body
x-www-form-urlencoded
username : user@email.com
password : user
grant_type : password
```  

> Api responses  

<table>
  <tr>
    <td>Request</td>
    <td>Response</td>
  </tr>
  <tr>
    <td>/api/home (with no token)</td>
    <td>Hello Anonymous!</td>
  </tr>
  <tr>
    <td>/api/home (with token)</td>
    <td>Hello {username}!</td>
  </tr>    
  <tr>
    <td>/api/me (with no token)</td>
    <td>401 error</td>
  </tr>
  <tr>
    <td>/api/me (with token)</td>
    <td>
      {"email" : "user@email.com", "roles" : ["USER"]}    
    </td>
  </tr>       
</table>

> Getting started  

```aidl

```