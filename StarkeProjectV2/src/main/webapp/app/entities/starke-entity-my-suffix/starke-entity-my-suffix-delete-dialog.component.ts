import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IStarkeEntityMySuffix } from 'app/shared/model/starke-entity-my-suffix.model';
import { StarkeEntityMySuffixService } from './starke-entity-my-suffix.service';

@Component({
    selector: 'jhi-starke-entity-my-suffix-delete-dialog',
    templateUrl: './starke-entity-my-suffix-delete-dialog.component.html'
})
export class StarkeEntityMySuffixDeleteDialogComponent {
    starkeEntity: IStarkeEntityMySuffix;

    constructor(
        private starkeEntityService: StarkeEntityMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.starkeEntityService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'starkeEntityListModification',
                content: 'Deleted an starkeEntity'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-starke-entity-my-suffix-delete-popup',
    template: ''
})
export class StarkeEntityMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ starkeEntity }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(StarkeEntityMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.starkeEntity = starkeEntity;
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
