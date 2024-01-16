package com.channel.channelapi.repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.channel.channelapi.model.Channel;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ChannelRepositoryTests {

        @Autowired
        private ChannelRepository channelRepository;

        @Test
        public void shouldCreateChannel() {
                Channel channel = Channel.builder().id(1L).createdDate(Instant.now()).updatedDate(Instant.now())
                                .content("Channel 1").build();

                Channel savedChannel = channelRepository.save(channel);

                Assertions.assertThat(savedChannel).isNotNull();
                Assertions.assertThat(savedChannel.getId()).isNotNull();
                Assertions.assertThat(savedChannel.getContent()).isEqualTo("Channel 1");
        }

        @Test
        public void shouldGetChannel() {
                Channel channel = Channel.builder()
                                .content("Channel 1").build();

                Channel savedChannel = channelRepository.save(channel);
                Channel retrievedChannel = channelRepository.findById(savedChannel.getId()).get();

                Assertions.assertThat(retrievedChannel).isNotNull();
                Assertions.assertThat(retrievedChannel.getId()).isNotNull();
                Assertions.assertThat(retrievedChannel.getContent()).isEqualTo("Channel 1");
        }

        @Test
        public void shouldGetAllChannels() {
                Channel channel = Channel.builder()
                                .content("Channel 1").build();
                Channel channel2 = Channel.builder()
                                .content("Channel 2").build();

                channelRepository.save(channel);
                channelRepository.save(channel2);
                List<Channel> retrievedChannels = channelRepository.findAll();

                Assertions.assertThat(retrievedChannels).isNotNull();
                Assertions.assertThat(retrievedChannels.size()).isEqualTo(2);
        }

        @Test
        public void shouldUpdateChannel() {
                Channel channel = Channel.builder()
                                .content("Channel 1").build();

                Channel savedChannel = channelRepository.save(channel);
                savedChannel.setContent("Updated Channel");
                Channel updatedChannel = channelRepository.save(savedChannel);

                Assertions.assertThat(updatedChannel).isNotNull();
                Assertions.assertThat(updatedChannel.getId()).isNotNull();
                Assertions.assertThat(updatedChannel.getContent()).isEqualTo("Updated Channel");
        }

        @Test
        public void shouldDeleteChannel() {
                Channel channel = Channel.builder()
                                .content("Channel 1").build();

                channelRepository.save(channel);
                channelRepository.deleteById(channel.getId());
                Optional<Channel> deletedChannel = channelRepository.findById(channel.getId());

                Assertions.assertThat(deletedChannel).isEmpty();
        }

        @Test
        public void shouldDeleteAllChannels() {
                Channel channel = Channel.builder()
                                .content("Channel 1").build();
                Channel channel2 = Channel.builder()
                                .content("Channel 2").build();

                channelRepository.save(channel);
                channelRepository.save(channel2);
                channelRepository.deleteAll();
                List<Channel> channels = channelRepository.findAll();

                Assertions.assertThat(channels.size()).isEqualTo(0);
        }

}
