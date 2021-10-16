package great.dog.api.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableJpaAuditing
@Configuration
public class WebConfig implements Filter, WebMvcConfigurer {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		//res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT");
		//res.setHeader("Access-Control-Max-Age", "3600");
		//res.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Origin, Content-Type, Accept, X-API-KEY");
        chain.doFilter(request, response);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
