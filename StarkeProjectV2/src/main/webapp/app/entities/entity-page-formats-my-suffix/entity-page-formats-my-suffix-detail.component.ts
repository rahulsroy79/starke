import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEntityPageFormatsMySuffix } from 'app/shared/model/entity-page-formats-my-suffix.model';

@Component({
    selector: 'jhi-entity-page-formats-my-suffix-detail',
    templateUrl: './entity-page-formats-my-suffix-detail.component.html'
})
export class EntityPageFormatsMySuffixDetailComponent implements OnInit {
    entityPageFormats: IEntityPageFormatsMySuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ entityPageFormats }) => {
            this.entityPageFormats = entityPageFormats;
        });
    }

    previousState() {
        window.history.back();
    }
}
