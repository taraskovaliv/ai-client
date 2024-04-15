package dev.kovaliv.cloudflare.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class AbstractModel {
    protected final String label;
}
