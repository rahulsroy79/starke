import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IEntityPageFormatsMySuffix } from 'app/shared/model/entity-page-formats-my-suffix.model';
import { EntityPageFormatsMySuffixService } from './entity-page-formats-my-suffix.service';
import { IEntityPageDetailsMySuffix } from 'app/shared/model/entity-page-details-my-suffix.model';
import { EntityPageDetailsMySuffixService } from 'app/entities/entity-page-details-my-suffix';

@Component({
    selector: 'jhi-entity-page-formats-my-suffix-update',
    templateUrl: './entity-page-formats-my-suffix-update.component.html'
})
export class EntityPageFormatsMySuffixUpdateComponent implements OnInit {
    private _entityPageFormats: IEntityPageFormatsMySuffix;
    isSaving: boolean;

    entitypagedetails: IEntityPageDetailsMySuffix[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private entityPageFormatsService: EntityPageFormatsMySuffixService,
        private entityPageDetailsService: EntityPageDetailsMySuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ entityPageFormats }) => {
            this.entityPageFormats = entityPageFormats;
        });
        this.entityPageDetailsService.query().subscribe(
            (res: HttpResponse<IEntityPageDetailsMySuffix[]>) => {
                this.entitypagedetails = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.entityPageFormats.id !== undefined) {
            this.subscribeToSaveResponse(this.entityPageFormatsService.update(this.entityPageFormats));
        } else {
            this.subscribeToSaveResponse(this.entityPageFormatsService.create(this.entityPageFormats));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEntityPageFormatsMySuffix>>) {
        result.subscribe(
            (res: HttpResponse<IEntityPageFormatsMySuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackEntityPageDetailsById(index: number, item: IEntityPageDetailsMySuffix) {
        return item.id;
    }
    get entityPageFormats() {
        return this._entityPageFormats;
    }

    set entityPageFormats(entityPageFormats: IEntityPageFormatsMySuffix) {
        this._entityPageFormats = entityPageFormats;
    }
}
