package com.januszcorporation.muslibryjkedition.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class SongCommand {
    private Long id;
    private String title;
    private String genre;
    private String ismn;
    private String year;
    private Long publisherId;
    private Long artistId;
}
