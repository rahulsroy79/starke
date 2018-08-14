import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEntityPageDetailsMySuffix } from 'app/shared/model/entity-page-details-my-suffix.model';
import { EntityPageDetailsMySuffixService } from './entity-page-details-my-suffix.service';

@Component({
    selector: 'jhi-entity-page-details-my-suffix-delete-dialog',
    templateUrl: './entity-page-details-my-suffix-delete-dialog.component.html'
})
export class EntityPageDetailsMySuffixDeleteDialogComponent {
    entityPageDetails: IEntityPageDetailsMySuffix;

    constructor(
        private entityPageDetailsService: EntityPageDetailsMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.entityPageDetailsService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'entityPageDetailsListModification',
                content: 'Deleted an entityPageDetails'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-entity-page-details-my-suffix-delete-popup',
    template: ''
})
export class EntityPageDetailsMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ entityPageDetails }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EntityPageDetailsMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.entityPageDetails = entityPageDetails;
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
