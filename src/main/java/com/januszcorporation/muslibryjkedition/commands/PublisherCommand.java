package com.januszcorporation.muslibryjkedition.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class PublisherCommand {
    private Long id;
    private String name;
    private String nip;
    private String address;
}
