import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IStarkeEntityMySuffix } from 'app/shared/model/starke-entity-my-suffix.model';

@Component({
    selector: 'jhi-starke-entity-my-suffix-detail',
    templateUrl: './starke-entity-my-suffix-detail.component.html'
})
export class StarkeEntityMySuffixDetailComponent implements OnInit {
    starkeEntity: IStarkeEntityMySuffix;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ starkeEntity }) => {
            this.starkeEntity = starkeEntity;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
