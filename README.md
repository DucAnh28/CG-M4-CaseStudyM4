# FootballClubManager

We made a simple management app for mini football club (Manchester United)

## Requirement

- Java 8 or higher
- gradle (development, optional)

## Configuration

See all available configuration in ```application.properties```. Config properties can be set directly in
application.properties, or application.properties file in the same folder with your war, or via env. You maybe need to
use your own .env file

### Default data generation

By default, app will generate some required data if not exist including: roles, permissions, default users, and one
default shelf. Default user include:

- **ADMIN** - account with role admin, can access almost every thing, see how many account each role .
- **COACH** - account with role coach, can access every thing with player but only can see the list of coach.
- **USER** - account with role user only can see list of player and coach without edit

All generated user using same password: 1.

Role and permission will be auto generated and setup for each logic. There are some special permissions that you cannot
delete:

- ```ADMIN```: special permission allow you to access everything.
- ```LIBRARIAN```: permission that allow you to access librarian app, without this permission, your account will only be
  a reader account and can not access library manage app, even if you have ```ADMIN``` permission
- ```FORCE```: permission that allow you to execute dangerous action, for example delete an author that affect related
  books

### Production

To run app in "production", you need to tweak some config properties in ```application.properties```

1. **Setup port**
    ```port
    server.port=${port:2828}
    ```
2. **Setup folder to save image**
    ```properties
    #uploadfile
    upload_file_avatar=${FILE_AVATAR}
    ```
3. **Setup Postgres database**
    ```properties
    # Change those configs to match your database. only postgresql supported
    spring.datasource.url      = jdbc:postgresql://localhost:5432/m4_casestudy
    spring.datasource.username = root
    spring.datasource.password = 123456
    ```
4. **Setup File Storage**
   ```properties
   # Example config using file as storage.
   ${FILE_AVATAR} = E:\\Code\\CG-M4-CaseStudyM4\\image\\
5. **Setup database**
    ```
   You need to create position , performance , status 
   for player before create player in your database