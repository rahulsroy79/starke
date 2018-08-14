import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEntityPageFormatsMySuffix } from 'app/shared/model/entity-page-formats-my-suffix.model';
import { EntityPageFormatsMySuffixService } from './entity-page-formats-my-suffix.service';

@Component({
    selector: 'jhi-entity-page-formats-my-suffix-delete-dialog',
    templateUrl: './entity-page-formats-my-suffix-delete-dialog.component.html'
})
export class EntityPageFormatsMySuffixDeleteDialogComponent {
    entityPageFormats: IEntityPageFormatsMySuffix;

    constructor(
        private entityPageFormatsService: EntityPageFormatsMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.entityPageFormatsService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'entityPageFormatsListModification',
                content: 'Deleted an entityPageFormats'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-entity-page-formats-my-suffix-delete-popup',
    template: ''
})
export class EntityPageFormatsMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ entityPageFormats }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EntityPageFormatsMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.entityPageFormats = entityPageFormats;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
