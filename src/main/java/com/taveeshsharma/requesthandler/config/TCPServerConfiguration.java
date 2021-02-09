package com.taveeshsharma.requesthandler.config;

import com.taveeshsharma.requesthandler.tcpserver.ServerSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.ip.dsl.Tcp;
import org.springframework.integration.ip.dsl.TcpInboundGatewaySpec;
import org.springframework.integration.ip.dsl.TcpServerConnectionFactorySpec;
import org.springframework.integration.ip.tcp.serializer.TcpCodecs;

@Configuration
@EnableIntegration
public class TCPServerConfiguration {

    @Value("${tcp.server.port}")
    private int serverSocketPort;

    @Autowired
    private ServerSocketHandler serverSocketHandler;

    @Bean
    public IntegrationFlow server(ServerSocketHandler serverSocketHandler) {
        TcpServerConnectionFactorySpec connectionFactory =
                Tcp.netServer(serverSocketPort)
                        .deserializer(TcpCodecs.lf())
                        .serializer(TcpCodecs.lf())
                        .soTcpNoDelay(true);

        TcpInboundGatewaySpec inboundGateway =
                Tcp.inboundGateway(connectionFactory);

        return IntegrationFlows
                .from(inboundGateway)
                .handle(this.serverSocketHandler)
                .get();
    }
}
