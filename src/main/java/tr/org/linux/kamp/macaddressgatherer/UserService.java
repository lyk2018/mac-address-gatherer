package tr.org.linux.kamp.macaddressgatherer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
@AllArgsConstructor
class UserService {

    private static final Pattern pattern = Pattern.compile(".*([0-9a-f][0-9a-f]([:-])[0-9a-f][0-9a-f](\\2[0-9a-f][0-9a-f]){4,8}).*");

    private UserRepository userRepository;

    @Async
    public void save(User user) {
        log.trace("User is saving... {}", user);
        user.setMacAddress(this.getMacAddressForIp(user.getIpAddress()));
        log.debug("User saved {}", user);
        userRepository.save(user);
    }

    Iterable<User> getAll() {
        return userRepository.findAll();
    }

    private String getMacAddressForIp(final String ip) {

        final ProcessBuilder processBuilder = new ProcessBuilder("arp", "-a");
        try {
            final Process process = processBuilder.start();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                if(line.contains(ip)) {
                    try {
                        process.destroyForcibly();
                    }
                    catch (Exception e){
                        log.error("ignoring exception during process destroy {}", e);
                    }
                    return extractMacAddress(line);
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return null;
        }
        return null;
    }

    private String extractMacAddress(String line) {
        log.trace("Extracting mac address from line {}", line);
        final Matcher matcher = pattern.matcher(line);
        if(matcher.matches()) {
            return matcher.group(1);
        }
        return null;
    }
}
