/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { Starkev2TestModule } from '../../../test.module';
import { EntityPageFormatsMySuffixDeleteDialogComponent } from 'app/entities/entity-page-formats-my-suffix/entity-page-formats-my-suffix-delete-dialog.component';
import { EntityPageFormatsMySuffixService } from 'app/entities/entity-page-formats-my-suffix/entity-page-formats-my-suffix.service';

describe('Component Tests', () => {
    describe('EntityPageFormatsMySuffix Management Delete Component', () => {
        let comp: EntityPageFormatsMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<EntityPageFormatsMySuffixDeleteDialogComponent>;
        let service: EntityPageFormatsMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Starkev2TestModule],
                declarations: [EntityPageFormatsMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(EntityPageFormatsMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EntityPageFormatsMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EntityPageFormatsMySuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
