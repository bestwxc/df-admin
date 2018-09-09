import net.df.generator.service.ServiceBaseMybatisGenerator;

public class Test {
    public static void main(String[] args) {

        ServiceBaseMybatisGenerator generator = new ServiceBaseMybatisGenerator();
        generator.setBasePackage("net.df.module");
        generator.setModuleName("admin");

        generator.setClassName("User");
        generator.setObjectName("user");
        generator.generate();
        generator.setClassName("Role");
        generator.setObjectName("role");
        generator.generate();
        generator.setClassName("Department");
        generator.setObjectName("department");
        generator.generate();
        generator.setClassName("Resource");
        generator.setObjectName("resource");
        generator.generate();
        generator.setClassName("RoleResource");
        generator.setObjectName("roleResource");
        generator.generate();
        generator.setClassName("TreeNode");
        generator.setObjectName("treeNode");
        generator.generate();
        generator.setClassName("UserRole");
        generator.setObjectName("userRole");
        generator.generate();

        generator.setClassName("AdministrativeDivision");
        generator.setObjectName("administrativeDivision");
        generator.generate();

        generator.setClassName("SystemMenu");
        generator.setObjectName("systemMenu");
        generator.generate();
    }
}
