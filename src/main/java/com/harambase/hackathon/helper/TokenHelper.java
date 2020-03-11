//package com.harambase.hackathon.helper;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Date;
//
//public class TokenHelper {
//
//    //expires_in: 3600 # 60 minutes
//    private static final int EXPIRES_IN = 3600;
//
//    private static final String APP_NAME = "PIONEER_CORE";
//    private static final String SECRET = "harambethegorilla";
//    private static final String AUTH_HEADER = "Authorization";
//    private static final String AUDIENCE_UNKNOWN = "unknown";
//    private static final String AUDIENCE_WEB = "web";
//    private static final String AUDIENCE_MOBILE = "mobile";
//    private static final String AUDIENCE_TABLET = "tablet";
//
//    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
//
//    public static String getUserIdFromToken(String token) {
//        String userId;
//        try {
//            final Claims claims = TokenHelper.getAllClaimsFromToken(token);
//            userId = claims.getSubject();
//        } catch (Exception e) {
//            userId = null;
//        }
//        return userId;
//    }
//
//    private static Date getIssuedAtDateFromToken(String token) {
//        Date issueAt;
//        try {
//            final Claims claims = TokenHelper.getAllClaimsFromToken(token);
//            issueAt = claims.getIssuedAt();
//        } catch (Exception e) {
//            issueAt = null;
//        }
//        return issueAt;
//    }
//
//    public static String getAudienceFromToken(String token) {
//        String audience;
//        try {
//            final Claims claims = TokenHelper.getAllClaimsFromToken(token);
//            audience = claims.getAudience();
//        } catch (Exception e) {
//            audience = null;
//        }
//        return audience;
//    }
//
//    public static String refreshToken(String token) {
//        String refreshedToken;
//        Date a = new Date();
//        try {
//            final Claims claims = TokenHelper.getAllClaimsFromToken(token);
//            claims.setIssuedAt(a);
//            refreshedToken = Jwts.builder()
//                    .setClaims(claims)
//                    .setExpiration(generateExpirationDate())
//                    .signWith(SIGNATURE_ALGORITHM, SECRET)
//                    .compact();
//        } catch (Exception e) {
//            refreshedToken = null;
//        }
//        return refreshedToken;
//    }
//
//    public static String generateToken(String userId, String[] role) {
//        String audience = generateAudience();
//        Claims claimsWithRole = Jwts.claims().setIssuer(APP_NAME)
//                .setSubject(userId)//NOTICE HERE: USERID REPLACE USERNAME
//                .setAudience(audience)
//                .setIssuedAt(new Date())
//                .setExpiration(generateExpirationDate());
//        claimsWithRole.put("rol", role);
//
//        return Jwts.builder()
//                .setClaims(claimsWithRole)
//                .signWith(SIGNATURE_ALGORITHM, SECRET)
//                .compact();
//    }
//
//    private static String generateAudience() {
//        return AUDIENCE_WEB;
//    }
//
//    private static Claims getAllClaimsFromToken(String token) {
//        Claims claims;
//        try {
//            claims = Jwts.parser()
//                    .setSigningKey(SECRET)
//                    .parseClaimsJws(token)
//                    .getBody();
//        } catch (Exception e) {
//            claims = null;
//        }
//        return claims;
//    }
//
//    private static Date generateExpirationDate() {
//        return new Date(new Date().getTime() + EXPIRES_IN * 1000);
//    }
//
//    public static int getExpiredIn() {
//        return EXPIRES_IN;
//    }
//
//    public static Boolean validateToken(String token, UserDetails userDetails) {
////        Person user = (Person) userDetails;
//        final String userId = getUserIdFromToken(token);
//        final Date created = getIssuedAtDateFromToken(token);
//        return (
//                userId != null &&
//                        userId.equals(userDetails.getUsername())
////                        && !isCreatedBeforeLastPasswordReset(created, user.getLastPasswordResetDate())
//        );
//    }
//
//    private static Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
//        return (lastPasswordReset != null && created.before(lastPasswordReset));
//    }
//
//    public static String getToken(HttpServletRequest request) {
//        /**
//         *  Getting the token from Authentication header
//         *  e.g Bearer your_token
//         */
//        String authHeader = TokenHelper.getAuthHeaderFromHeader(request);
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            return authHeader.substring(7);
//        }
//
//        return null;
//    }
//
//    private static String getAuthHeaderFromHeader(HttpServletRequest request) {
//        return request.getHeader(AUTH_HEADER);
//    }
//
//}
