package dev.pigmeo.greenhouse_kt.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(
    basePackages = ["dev.pigmeo.greenhouse_kt.infrastructure.repository"],
)
class PersistenceConfiguration