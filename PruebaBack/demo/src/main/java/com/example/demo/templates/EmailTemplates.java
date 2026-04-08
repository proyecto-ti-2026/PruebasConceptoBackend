package com.example.demo.templates;


public class EmailTemplates {

    public static String resetPassword(String resetLink) {
        return """
            <div style="font-family: Arial, sans-serif; max-width: 600px; margin: 0 auto; padding: 20px;">
                
                <!-- Logo -->
                <div style="text-align: center; margin-bottom: 30px;">
                    <img src="https://i.postimg.cc/15z1n106/Logo-definitivo.jpg" alt="A la mesa" width="60"/>
                    <p style="font-weight: bold; font-size: 16px; margin: 5px 0;">A la mesa</p>
                </div>

                <!-- Cuerpo -->
                <p style="color: #333; font-size: 14px;">
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
                    Aliquam tincidunt elementum sem non luctus.
                </p>

                <!-- Link -->
                <p>
                    <a href="%s" style="color: #E87722; text-decoration: none; font-size: 14px;">
                        Reinicio de contraseña
                    </a>
                </p>

                <p style="color: #333; font-size: 14px;">
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
                    Aliquam tincidunt elementum sem non luctus.
                </p>

            </div>
        """.formatted(resetLink);
    }
}